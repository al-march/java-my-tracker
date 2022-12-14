const PROXY_CONFIG = {
    "/": {
      "target": "http://127.0.0.1:8080/",
      "secure": false,
      "changeOrigin": true,
      "bypass": function (req) {
        if (req.headers.accept.indexOf("html") !== -1) {
          console.log("Skipping proxy for browser request.");
          return "/index.html";
        }
        req.headers["X-Custom-Header"] = "yes";
      }
    }
  }

  module.exports = PROXY_CONFIG;
