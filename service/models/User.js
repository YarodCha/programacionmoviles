const Sequelize = require("sequelize");
const sequelize = require("../database");

const Model = Sequelize.Model;
class User extends Model {}
User.init(
  {
    // attributes
    username: {
      type: Sequelize.STRING,
      allowNull: false
    },
    password: {
      type: Sequelize.STRING,
      allowNull: false
    },
    firstName: {
      type: Sequelize.STRING,
      allowNull: false
    },
    lastName: {
      type: Sequelize.STRING,
      allowNull: false
    }
  },
  {
    underscored: true,
    sequelize,
    modelName: "user"
    // options
  }
);

module.exports = User;
