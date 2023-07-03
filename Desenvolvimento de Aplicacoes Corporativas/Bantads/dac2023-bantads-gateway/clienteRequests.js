import httpProxy from "express-http-proxy";
const httpProxyItem = httpProxy

export const insereAutocadastro = httpProxyItem("http://localhost:8084", {
  proxyReqBodyDecorator: function (bodyContent, originalReq) {

      return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, originalReq) {
    proxyReqOpts.headers["Content-Type"] = "application/json";
    proxyReqOpts.method = "POST";
    return proxyReqOpts;
  },
  userResDecorator: function (proxyRes, proxyResData, userReq, userRes) {
    if (proxyRes.statusCode == 200) {
      let str = Buffer.from(proxyResData).toString("utf-8");
      let objBody = JSON.parse(str);
      userRes.status(200);
      return { data: objBody };
    } else {
      userRes.status(400);
      return { auth: false, message: "Falha ao autocadastrar"};
    }
  },
});

export const editaCliente = httpProxyItem("http://localhost:8084", {
  proxyReqBodyDecorator: function (bodyContent, originalReq) {

      return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, originalReq) {
    proxyReqOpts.headers["Content-Type"] = "application/json";
    proxyReqOpts.method = "PUT";
    return proxyReqOpts;
  },
  userResDecorator: function (proxyRes, proxyResData, userReq, userRes) {
    if (proxyRes.statusCode == 200) {
      let str = Buffer.from(proxyResData).toString("utf-8");
      let objBody = JSON.parse(str);
      userRes.status(200);
      return { data: objBody };
    } else {
      userRes.status(400);
      return { auth: false, message: "Falha ao editar cliente"};
    }
  },
});

export const getCliente = httpProxyItem("http://localhost:8084", {
  proxyReqBodyDecorator: function (bodyContent, originalReq) {

      return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, originalReq) {
    proxyReqOpts.headers["Content-Type"] = "application/json";
    proxyReqOpts.method = "GET";
    return proxyReqOpts;
  },
    userResDecorator: function (proxyRes, proxyResData, userReq, userRes) {
    if (proxyRes.statusCode == 200) {
      let str = Buffer.from(proxyResData).toString("utf-8");
      let objBody = JSON.parse(str);
      userRes.status(200);
      
      return { data: objBody };
    } else {
      userRes.status(400);
      return { auth: false, message: "Falha ao buscar cliente"};
    }
  },
});

export const getClienteCpf = httpProxyItem("http://localhost:8084", {
  proxyReqBodyDecorator: function (bodyContent, originalReq) {

      return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, originalReq) {
    proxyReqOpts.headers["Content-Type"] = "application/json";
    proxyReqOpts.method = "GET";
    return proxyReqOpts;
  },
    userResDecorator: function (proxyRes, proxyResData, userReq, userRes) {
    if (proxyRes.statusCode == 200) {
      let str = Buffer.from(proxyResData).toString("utf-8");
      let objBody = JSON.parse(str);
      userRes.status(200);
      return { data: objBody };
    } else {
      userRes.status(400);
      return { auth: false, message: "Falha ao buscar cpf"};
    }
  },
});