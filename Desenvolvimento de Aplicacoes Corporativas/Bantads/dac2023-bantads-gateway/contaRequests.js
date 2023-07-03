import httpProxy from "express-http-proxy";
const httpProxyItem = httpProxy;

export const insereConta = httpProxyItem("http://localhost:8083", {
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
      return { auth: false, message: "Falha ao criar conta" };
    }
  },
});

export const insereMovimentacoes = httpProxyItem("http://localhost:8083", {
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
      userRes.status(200);
      return { data: "Movimentaçao ok" };
    } else {
      userRes.status(400);
      console.log(Buffer.from(proxyResData).toString("utf-8"))
      return { message: "Falha ao movimentar" };
    }
  },
});

export const extrato = httpProxyItem("http://localhost:8083", {
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
      return { message: "Falha ao obter extrato" };
    }
  },
});

export const getContaPorId = httpProxyItem("http://localhost:8083", {
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
      return {
        auth: false,
        message: "Falha ao buscar conta com o id informado",
      };
    }
  },
});

export const getContaPorIdCliente = httpProxyItem("http://localhost:8083", {
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
      return {
        auth: false,
        message: "Falha ao buscar conta com o id informado",
      };
    }
  },
});
