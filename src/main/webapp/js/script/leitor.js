'use strict'
var synth = window.speechSynthesis;
var utterance = null;
var recognition = null;

var getAudio = {
	principal: {
		intro:' Página principal. Bem vindo ao Portal do Ledor. Diga notícia para escutar as últimas notícias. Diga outros para saber como acessar as outras páginas. Para navegar entre as notícias diga próximo, anterior ou repetir ',
		semNoticia:' Não existem notícias publicadas. ',
		ultimaNoticia:' Todas as notícias foram lidas, digar anterior para voltar a notícia anterior ou diga notícia para começar a ler da primeira notícia. ',
		primeiraNoticia:' Esta é a primeira notícias digar próximo para ler a próxima notícia ou repetir para escutar a notícia outra vez. ',
		fraseAjuda:' Para acesso a outras páginas você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. '
		//Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login.
	},
	contato: {
		intro:' Página de contato. O telefone para contato é 61 3901 7607 e o e-mail é portalLedor@educacional.com.br. ',
		fraseAjuda:' Para acesso a outras páginas você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. '
	},
	localizacao: {
		intro:' Página de Localização. O Centro de Ensino Especial de Deficientes Visuais está localizado na S G A S 2 Quadra 612 sul - asa sul, Brasília DF. CEP 70200-000. ',
		fraseAjuda:' Para acesso a outras páginas você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. '
	},
	login: {
		intro:' Página login. Diga usuário para informar um usuário, senha para informar uma senha. Diga entrar para submeter o usuário e senha informado para ter acesso ao sistema. Caso a senha ou o usuário estejam incorretos diga o nome do campo que queira corrigir e o informe outra vez. ',
		fraseAjuda:' Para acesso a outras páginas você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. ',
		informeUsuario:' Por favor informe um usuário. ',
		informeSenha:' Por favor informe uma senha. ',
		informeUsuarioSenha:' Por favor Informe uma senha e um usuário. ',
		usuarioSenhaIncorreto:' Senha ou usuário incorreto. '
	},
	menu: {
		intro:'Menu de seleção de funcionalidades. Diga áudio para procurar um áudio livro, agenda para verificar a agenda das aulas marcadas para o clube do ledor, sair para encerrar a sessão atual.',
		fraseAjuda:' Para acesso a outras páginas você tem as seguintes opções, diga áudio para procurar um áudio livro, agenda para verificar a agenda de aulas marcadas para o clube do ledor, sair para encerrar a sessão atual. '
	},
	agenda: {
		intro:'Página de Agenda de aulas. ',
		comandos:' Diga próximo para consultar o próximo dia, anterior para consultar o dia anterior e repetir para escutar a agenda do dia outra vez. Diga voltar para retornar ao menu de seleção de funcionalidades ',
		fraseAjuda:' Para acesso a outras páginas você tem as seguintes opções, diga áudio para procurar um áudio livro, agenda para verificar a agenda de aulas marcadas para o clube do ledor, sair para encerrar a sessão atual. '
	},
	audio: {
		intro:' Página de Leitura de áudio Livro. Diga informações para escutar sobre este áudio livro, reproduzir para iniciar a reprodução, parar para pausar a reprodução, retroceder para voltar 30 segundos o áudio, avançar para adiantar 30 segundos o áudio, aumentar para aumentar o volume, abaixar para diminuir o volume, repetir para reiniciar o áudio e voltar para retornar a tela de lista de áudio livros. ',
		fraseAjuda:' Para acesso a outras páginas você tem as seguintes opções, diga áudio para procurar um áudio livro, agenda para verificar a agenda de aulas marcadas para o clube do ledor, sair para encerrar a sessão atual. ',
		inicioAudio:'Início do áudio atingido.',
		finalAudio:'Final do áudio atingido.'
	},audioBusca: {
		intro:'Página de Busca de áudio livro, diga termo para informar um termo para busca de áudio livro. Depois de informar um termo diga busca para procurar um áudio livro pelo termo. Diga voltar para retornar ao menu de funcionalidades. ',
		fraseAjuda:' Para acesso a outras páginas você tem as seguintes opções, diga áudio para procurar um áudio livro, agenda para verificar a agenda de aulas marcadas para o clube do ledor, sair para encerrar a sessão atual. ',
		nenhumTermo:'Nenhum termo informado, por favor informe uma palavra para busca de áudio livro.',
		informeTermo:'Diga um termo para a busca.'
	},audioLista: {
		intro:' Página de resultado de Consulta de áudio livro.',
		comandos: ' Diga resultados para verificar a lista de resultados. Diga próximo, repetir e anterior para navegar pela lista. Diga escutar para acessar o áudio livro. ',
		comandos2: ' Diga próximo, repetir e anterior para navegar pela lista. Diga escutar para acessar o áudio livro. ',
		naoEncontrado:'Não foram encontrados áudio livros com o termo informado, diga voltar para retornar a tela de busca e tentar outro termo.',
		semResultado:'Não existem resultados para a busca.',
		ultimoResultado:'Você está no último resultado, digar anterior para voltar ao resultado anterior ou diga resultados para começar a percorrer do primeiro resultado.',
		primeiroResultado:'Não existem resultados anteriores ao atual.',
		fraseAjuda:' Para acesso a outras páginas você tem as seguintes opções, diga áudio para procurar um áudio livro, agenda para verificar a agenda de aulas marcadas para o clube do ledor, sair para encerrar a sessão atual. '
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
/*	console.log(frase);
	synth.cancel();
	utterance = new SpeechSynthesisUtterance(frase);
	utterance.lang = 'pt-BR';
	utterance.rate = 2;
	synth.speak(utterance);*/

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
