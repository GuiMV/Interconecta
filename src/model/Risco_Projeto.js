// cd "C:\Users\ASUS\Desktop\BD - Interconecta\src\model"
// node Risco_Projeto.js

const db = require('./db');
const Risco_Projeto = db.sequelize.define('Risco_Projetos', {
    Id: {
        type: db.Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    Cod_Risco: {
        type: db.Sequelize.INTEGER,
        references: {
            model: 'riscos',
            key: 'Codigo',
        }
    },
    Probabilidade: {
        type: db.Sequelize.FLOAT
    },
    Impacto: {
        type: db.Sequelize.FLOAT
    }    
}, {
    timestamps: false,
  });

Risco_Projeto.sync({force: true});// Warning! Isso recria a tabela do 0
module.exports = Risco_Projeto;
