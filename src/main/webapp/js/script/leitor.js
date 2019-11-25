'use strict'
var synth = window.speechSynthesis;
var utterance = null;
var recognition = null;

var getAudio = {
	principal: {
		intro:'Página principal. Bem vindo ao Portal do Ledor. Diga notícia para escutar as últimas notícias. Diga outros para saber como acessar as outras páginas.',
		semNoticia:'Não existem notícias publicadas.',
		ultimaNoticia:'Todas as noticias foram lidas, digar anterior para voltar a noticia anterior ou diga noticia para começar a ler da primeira noticia.',
		fraseAjuda:'Você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login.'
	},
	contato: {
		intro:'Página de contato. O telefone para contato é 61 3901 7607.',
		fraseAjuda:'Você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login.'
	},
	localizacao: {
		intro:'Página de Localização. O Centro de Ensino Especial de Deficientes Visuais está localizado na SGAS II Quadra 612 Sul - Asa Sul, Brasília – DF. CEP: 70200-000.',
		fraseAjuda:'Você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login.'
	},
	login: {
		intro:'Página login. Diga usuário para informar um usuário via teclado, senha para informar uma senha. Aperte seta para baixo para submeter o usuário e senha. Caso a senha ou o usuário estejam incorretos diga o nome do campo que queira corrigir e digite outra vez.',
		fraseAjuda:'Você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login.',
		informeUsuario:'Por favor informe um usuário.',
		informeSenha:'Por favor informe uma senha.',
		informeUsuarioSenha:'Por favor Informe uma senha e um usuário.',
		usuarioSenhaIncorreto:'Senha ou usuário incorreto.'
	},
	menu: {
		intro:'Menu de seleção de funcionalidades. Diga áudio para procura um áudio livro, agenda para verificar a agenda de aulas marcadas para o clube do ledor, sair para encerrar a sessão atual.',
		fraseAjuda:'Você tem as seguintes opções, diga áudio para procura um áudio livro, agenda para verificar a agenda de aulas marcadas para o clube do ledor, sair para encerrar a sessão atual. Você pode também apertar os botões de um a três no teclado numérico para as respectivas opções, áudio, agenda e sair'
	},
	agenda: {
		intro:'Página de Agenda de aulas.',
		fraseAjuda:'Você tem as seguintes opções, diga áudio para procura um áudio livro, agenda para verificar a agenda de aulas marcadas para o clube do ledor, sair para encerrar a sessão atual. Você pode também apertar os botões de um a três no teclado numérico para as respectivas opções, áudio, agenda e sair'
	},
	ajuda:'Descupa não entendi, por favor repita. Em caso de dúvida diga ajuda'
};

var getTexto = {
	principal: {
		erroNoticia:'Não foi possivel recuperar as notícias.'
	},
	ajuda:'Descupa não entendi, por favor repita. Em caso de dúvida diga ajuda'
};

var reproduzirFrase = function(frase) {

	synth.cancel();
	utterance = new SpeechSynthesisUtterance(frase);
	utterance.lang = 'pt-BR';
	utterance.rate = 2;
	synth.speak(utterance);

};

var comecarReconhecimento = function(){

	recognition = new webkitSpeechRecognition();
	recognition.interimResults = true;
	recognition.lang = 'pt-BR';
	recognition.continuous = true;
	recognition.start();

	recognition.onend = function() {
		recognition.abort();
		recognition.start();
	};

	recognition.onerror = function(event) {
		recognition.abort();
		recognition.start();
	};

};
