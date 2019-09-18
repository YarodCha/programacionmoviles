const { Router } = require("express");
const router = Router();
const User = require("../models/User");

router.get("/", async (req, res) => {
  const users = await User.findAll();
  res.json(users);
});

router.get("/:id", async (req, res) => {
  const user = await User.findOne({ where: { id: req.params.id } });
  res.json(user);
});

router.post("/", async (req, res) => {
  const { username, password, firstName, lastName } = req.body;
  const user = await User.create({ username, password, firstName, lastName });
  res.json(user);
});

router.delete("/:id", async (req, res) => {
  const user = await User.findOne({ where: { id: req.params.id } });
  const respuesta = user.dataValues;
  await user.destroy();
  res.json(respuesta);
});

router.put("/:id", async (req, res) => {
  const { username, password, firstName, lastName } = req.body;
  const user = await User.findOne({ where: { id: req.params.id } });
  await user.update({ username, password, firstName, lastName });
  res.json(user.dataValues);
});

module.exports = router;
