// cd "C:\Users\ASUS\Desktop\BD - Interconecta\src\model"
// node Risco.js

const db = require('./db');
const Riscos = db.sequelize.define('Riscos', {
    Codigo: {
        type: db.Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    Nome: {
        type: db.Sequelize.STRING,
    },
    Descricao: {
        type: db.Sequelize.TEXT,
    },
}, {
    timestamps: false,
  });

//Risco.sync({force: true});// Warning! Isso recria a tabela do 0
module.exports = Riscos;
