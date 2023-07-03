import { config } from "dotenv-safe";
import express from "express";
import bodyParser from "body-parser";
import cookieParser from "cookie-parser";
import logger from "morgan";
import helmet from "helmet";
import httpProxy from "express-http-proxy";
import http from "http";
import fetch from "node-fetch";
import jwt from "jsonwebtoken";

import * as contaServiceProxy from "./contaRequests.js";
import * as clienteServiceProxy from "./clienteRequests.js";
import * as gerenteServiceProxy from "./gerenteRequests.js";
import * as authServiceProxy from "./authRequests.js";
import * as sagaServiceProxy from "./sagaRequests.js";

const app = express();

config();

let urlEncodedParser = bodyParser.urlencoded({ extended: false });

app.use(logger("dev"));
app.use(helmet());
app.use(express.json());
app.use(cookieParser());

app.use(bodyParser.urlencoded({ extended: false }));

function verifyJWT(req, res, next) {
  const token = req.headers["x-access-token"];
  if (!token) {
    return res
      .status(401)
      .json({ auth: false, message: "Token não fornecido" });
  } else {
    jwt.verify(token, process.env.JWT_SECRET, (err, decoded) => {
      if (err) {
        return res.status(500).json({ auth: false, message: "Token inválido" });
      } else {
        req.userId = decoded.id;
        next();
      }
    });
  }
}

//auth
app.post("/login", urlEncodedParser, (req, res, next) => {
  authServiceProxy.autentica(req, res, next);
});

//conta
app.post("/contas", verifyJWT, (req, res, next) => {
  contaServiceProxy.insereConta(req, res, next);
});

app.post("/movimentacoes", verifyJWT, (req, res, next) => {
  contaServiceProxy.insereMovimentacoes(req, res, next);
});

app.post("/movimentacoes/data", verifyJWT, (req, res, next) => {
  contaServiceProxy.extrato(req, res, next);
});

app.get("/contas/:id", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;
  contaServiceProxy.getContaPorId(req, res, next);
});

app.get("/contas/cliente/:id", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;
  contaServiceProxy.getContaPorIdCliente(req, res, next);
});
//cliente
app.post("/customers", urlEncodedParser, (req, res, next) => {
  sagaServiceProxy.insereAutocadastro(req, res, next);
});
app.put("/customers", urlEncodedParser, (req, res, next) => {
  sagaServiceProxy.editaAutocadastro(req, res, next);
});

app.put("/clientes/:id", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;

  clienteServiceProxy.editaCliente(req, res, next);
});

app.get("/clientes/by-cpf/:id", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;

  clienteServiceProxy.getCliente(req, res, next);
});

app.get("/clientes/:id", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;

  clienteServiceProxy.getCliente(req, res, next);
});

app.get("/clientes", verifyJWT, (req, res, next) => {

  clienteServiceProxy.getCliente(req, res, next);
});


app.get("/cliente/by-cpf/:cpf", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;

  clienteServiceProxy.getClienteCpf(req, res, next);
});

//gerente
app.post("/gerentes", verifyJWT, (req, res, next) => {
  gerenteServiceProxy.insereGerente(req, res, next);
});
app.put("/gerentes/:id", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;

  gerenteServiceProxy.alteraGerente(req, res, next);
});

app.delete("/gerentes/:id", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;

  gerenteServiceProxy.deleteGerente(req, res, next);
});

app.get("/gerentes", verifyJWT, (req, res, next) => {
  gerenteServiceProxy.listarGerentes(req, res, next);
});

app.get("/clientes-gerente", verifyJWT, (req, res, next) => {
  gerenteServiceProxy.listarClientesGerenteConta(req, res, next);
});

app.put("/clientes-gerente/:id", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;

  gerenteServiceProxy.aprova(req, res, next);
});

app.get("/gerente/:cpf", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;

  gerenteServiceProxy.getGerentesById(req, res, next);
});

app.delete("/gerente/:cpf", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;

  gerenteServiceProxy.deleteGerenteById(req, res, next);
});

app.put("/gerente/:id", verifyJWT, (req, res, next) => {
  req.url = req.originalUrl;

  gerenteServiceProxy.editaGerente(req, res, next);
});
let server = http.createServer(app);
server.listen(process.env.PORT || 3000);
