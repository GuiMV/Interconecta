//Importando o modulo 'sequelize'
const Sequelize = require('sequelize');

// Conexão com o BD  
const sequelize = new Sequelize('base_dados', 'root', '202110040021', {
    host: "localhost",
    dialect: 'mysql'
});

module.exports = {
    Sequelize: Sequelize,
    sequelize: sequelize
}