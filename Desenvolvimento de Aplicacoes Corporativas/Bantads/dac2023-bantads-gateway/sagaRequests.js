import httpProxy from "express-http-proxy";
const httpProxyItem = httpProxy


export const insereAutocadastro = httpProxyItem("http://localhost:8082", {
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
        console.log(Buffer.from(proxyResData).toString("utf-8"))
      userRes.status(400);
      return { auth: false, message: "Falha ao autocadastrar"};
    }
  },
});


export const editaAutocadastro = httpProxyItem("http://localhost:8082", {
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
        console.log(Buffer.from(proxyResData).toString("utf-8"))
      userRes.status(400);
      return { auth: false, message: "Falha ao editar"};
    }
  },
});