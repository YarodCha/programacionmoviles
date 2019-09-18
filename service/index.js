const express = require("express");
const morgan = require("morgan");

//Initializations
const app = express();
const sequelize = require("./database");

//LOGS
console.log(sequelize.options.timezone);
console.log(new Date().toString());

//Creating tables
sequelize
  .sync({ force: false })
  .catch(() =>
    console.log("Ha ocurrido un error a la hora de crear las bases de datos")
  );

//Settings
app.set("port", process.env.PORT || 3000);

//Midlewares
app.use(morgan("dev"));
app.use(express.urlencoded({ extended: false }));
app.use(express.json());

//API
app.use("/api/usuarios", require("./routes/usuarios"));

//Start the server
app.listen(app.get("port"), () => {
  console.log("Server on port", app.get("port"));
});
