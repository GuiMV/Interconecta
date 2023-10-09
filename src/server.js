// cd "C:\Users\ASUS\Desktop\BD - Interconecta\src" -> npm start

//Biblioteca express (import)
const express = require('express');
//Atribuição dos métodos do express (criação de variável)
const app = express();
//Porta do localhost
const porta = 3000;
//Importando o modulo handlebars
const handlebars = require('express-handlebars');
//Importando o modulo body-parser
const bodyParser = require('body-parser');
//Importando o modulo path
const path = require('path');
//Importando o modulo fs
const fs = require('fs');
//Direcionamento de arquivo de registros
const dataFile = __dirname + '/registros.txt';
//Importando o model 'Post'
const Risco_Projeto = require('./model/Risco_Projeto');
//Importando o model 'Risco'
const Riscos = require('./model/Riscos');

// Configuração: Template Engine
    app.engine('handlebars', handlebars.engine({ defaultLayout: 'main' }));
    app.set('view engine', 'handlebars'); 
// Configuração: Body-Parser
    app.use(bodyParser.urlencoded({extended: false}))
    app.use(bodyParser.json())   

//Lida com a análise de dados
app.use(express.urlencoded({extended: true}));
//Serve os arquivos estáticos, como o CSS
app.use(express.static(path.join(__dirname + "/view/css")));

// Rotas
    //Criação da rota raiz, Página 'Login'
    app.get('/', (req, res) => {
        console.log('Usuário na pagina de Login')
        res.sendFile(path.join(__dirname + '/views/Login.html'))
    })

    //criação da rota do css da página de 'Login'
    app.get('/css/Style.css', (req, res) => {
        res.setHeader('Content-Type', 'text/css');
        res.sendFile(path.join(__dirname + '/views/css/Style.css'));
    });

    //Ler o arquivo de 'registros' e retorna para o JavaScript do lado do cliente, Página 'Login'
    app.get('/get-users', (req, res) => {
        fs.readFile(dataFile, 'utf8', (err, data) => {
            if (err) {
                res.status(500).send(err);
                return;
            }

            // Verifique se o conteúdo do arquivo não está vazio
            if (!data.trim()) {
                res.status(500).send("O arquivo está vazio.");
                return;
            }

            let userRecords;

            try {
                userRecords = data.split('\n').filter(Boolean).map(JSON.parse);
            } catch {
                res.status(500).send("O arquivo não contém um JSON válido.");
                return;
            }
            res.json(userRecords);
        });
    });

    //criação da rota da Página 'Interface'
    app.get('/Interface', async (req, res) => {
        console.log('Usuário na Interface do projeto');
        try{
            const riscos = await Riscos.findAll();
            const riscosSimples = riscos.map(risco => risco.dataValues); // Desembrulha os dados
            res.render('Interface', { riscos: riscosSimples });
        } catch (error){
            console.error(error);
            res.status(500).send('Erro ao buscar riscos no banco de dados');
        }              
    });

    app.post('/envio', function(req, res){
        Risco_Projeto.create({
            Cod_Risco: req.body.risco,
            Probabilidade: req.body.probabilidade,
            Impacto: req.body.impacto,
        }).then(function(){
            res.redirect('/Interface');
        }).catch(function(erro){
            res.send("Houve um erro: " + erro)
        })
    })

// Criação da rota do css da Página 'Interface'
    app.get('/css/Style_Interface.css', (req, res) => {
        res.setHeader('Content-Type', 'text/css');
        res.sendFile(path.join(__dirname + '/views/css/Style_Interface.css'));
    });

    app.get('/deletar/:id', (req, res) => {
        Risco_Projeto.destroy({where: {'id': req.params.id}}).then(() => {
            res.send("Cadastro de risco deletado!")
        }).catch((erro) => {
            res.send("Este risco não existe!")
        })
    })

//Inicializando o servidor
app.listen(porta, () => {
    console.log(`Servidor foi iniciado! Acesso: http://localhost:${porta}`)
})