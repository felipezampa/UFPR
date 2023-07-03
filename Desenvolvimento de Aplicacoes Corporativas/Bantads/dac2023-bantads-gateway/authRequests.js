import httpProxy from "express-http-proxy";
import jwt from "jsonwebtoken";

const httpProxyItem = httpProxy;

export const autentica = httpProxyItem("http://localhost:8090", {
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
      const id = objBody.id;
      const token = jwt.sign({ id }, process.env.JWT_SECRET, {
        expiresIn: "50000h",
      });
      userRes.status(200);
      return { auth: true, token: token, data: objBody };
    } else {
      userRes.status(401);
      return { auth: false, message: "Login ou senha inválidos" };
    }
  },
});

