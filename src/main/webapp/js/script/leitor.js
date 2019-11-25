'use strict'

var synth = window.speechSynthesis;
var utterance = null;
var recognition = null;
var fraseAjuda = 'Você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login.' ;

var reproduzirFrase = function(frase) {
	synth.cancel();
	utterance = new SpeechSynthesisUtterance(frase);
	utterance.lang = 'pt-BR';
	utterance.rate = 2;
	synth.speak(utterance);
}

var comecarReconhecimento = function(){
	recognition = new webkitSpeechRecognition();
	recognition.interimResults = true;
	recognition.lang = "pt-BR";
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

}
