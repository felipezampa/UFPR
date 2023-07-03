import httpProxy from "express-http-proxy";
import axios from "axios";

const httpProxyItem = httpProxy;

export const insereGerente = httpProxyItem("http://localhost:5002", {
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
      return { auth: false, message: "Falha ao criar gerente" };
    }
  },
});

export const alteraGerente = httpProxyItem("http://localhost:5002", {
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
      return { auth: false, message: "Falha ao criar gerente" };
    }
  },
});

export const deleteGerente = httpProxyItem("http://localhost:5002", {
  proxyReqBodyDecorator: function (bodyContent, originalReq) {
    return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, originalReq) {
    proxyReqOpts.headers["Content-Type"] = "application/json";
    proxyReqOpts.method = "DELETE";
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
      return { auth: false, message: "Falha ao criar gerente" };
    }
  },
});


export const listarGerentes = httpProxyItem("http://localhost:5002", {
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
      return { auth: false, message: "Falha ao listar gerentes" };
    }
  },
});

export const listarClientesGerenteConta = httpProxyItem(
  "http://localhost:5002",
  {
    proxyReqBodyDecorator: function (bodyContent, originalReq) {
      return bodyContent;
    },
    proxyReqOptDecorator: function (proxyReqOpts, originalReq) {
      proxyReqOpts.headers["Content-Type"] = "application/json";
      proxyReqOpts.method = "GET";
      return proxyReqOpts;
    },
    userResDecorator: async function (
      proxyRes,
      proxyResData,
      userReq,
      userRes
    ) {
      if (proxyRes.statusCode == 200) {
        let str = Buffer.from(proxyResData).toString("utf-8");

        let objBody = JSON.parse(str);
        //   objBody.forEach( (element, index) => {
        //   let link = "http://localhost:8083/contas/cliente/" + element.saga;
        //   await axios({
        //     method: "get",
        //     url: link,
        //   })
        //     .then((response) => {
        //       objBody[index]["conta"] = response.data;

        //       console.log(objBody[index]);
        //     })
        //     .catch((err) => {
        //       objBody[index]["conta"] = {};
        //     });
        // });

        userRes.status(200);

        return { data: objBody };
      } else {
        userRes.status(400);
        return { auth: false, message: "Falha ao listar gerentes" };
      }
    },
  }
);

export const getGerentesById = httpProxyItem("http://localhost:5002", {
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
      return { auth: false, message: "Falha ao buscar id de gerente" };
    }
  },
});

export const deleteGerenteById = httpProxyItem("http://localhost:5002", {
  proxyReqBodyDecorator: function (bodyContent, originalReq) {
    return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, originalReq) {
    proxyReqOpts.headers["Content-Type"] = "application/json";
    proxyReqOpts.method = "DELETE";
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
      return { auth: false, message: "Falha ao deletar gerente" };
    }
  },
});

export const editaGerente = httpProxyItem("http://localhost:5002", {
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
      return { auth: false, message: "Falha ao editar gerente" };
    }
  },
});

export const aprova = httpProxyItem("http://localhost:5002", {
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
      return { auth: false, message: "Falha ao aprovar cliente" };
    }
  },
});
