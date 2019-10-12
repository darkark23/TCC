'use strict'
angular
		.module('tccApp')
		.controller(
				'PrincipalController',
				[
						"$scope",
						'$state',
						'principalService',
						'$rootScope',
						function($scope, $state, principalService, $rootScope) {

							var synth = window.speechSynthesis;
							var utterance = null;
							var recognition = null;
							var frasePrincipal = 'Página principal. Bem vindo ao Portal do Ledor. Diga notícia para escutar as últimas notícias. Diga outros para saber como acessar as outras páginas.';
							var fraseAjuda = 'Você tem as seguintes opções, diga principal para acessar a página principal, contato para página com informações de contato da instituição, localização para página com informação de localização da instituição, login para acessar a página para entrada do sistema. Você pode também apertar os botões de um a quatro no teclado numérico para as respectivas páginas, principal, contato, localização e login.' ;
							var noticia = 'Notícia 01. Título. Designer cria fonte que une braile ao alfabeto tradicional. Publicado em 17 março de 2018, as 16 horas  e 21 minutos. São Paulo – Um designer japonês teve uma ideia brilhante para tentar incluir ainda mais os deficientes visuais no dia-a-dia da sociedade: criar uma fonte tipográfica que une a escrita tradicional ao braile. A lógica por trás disso é permitir que cegos e outras pessoas possam compartilhar de uma mesma comunicação em um espaço comum. Como? Da seguinte maneira: os pontos do braile aprecem em alto relevo e integrados aos caracteres tradicionais do alfabeto latino. O designer Kosuke Takahashi, criador da fonte Braile Neue, propõe que essa tipografia seja adotada já nos próximos Jogos Olímpicos, que ocorrem daqui a dois anos, em Tóquio, capital do Japão.	Mas há um problema: geralmente a comunicação em braile tende a ser pequena e quase invisível. Por isso, Takahashi diz que realizou uma pesquisa para ver se uma larga sinalização com a linguagem para deficientes visuais poderia ser legível a eles. “Descobri que é possível, contanto que exista um padrão de até seis pontos, independentemente do tamanho”, diz o designer, em seu website.“Hoje em dia, raramente vemos o braile implementado nos lugares públicos, uma vez que essa comunicação necessita de um espaço adicional, sendo considerada por aqueles que têm visão como algo não importante”, diz Takahashi.“A Braille Neue aborda essa questão, ao tornar o braile mais acessível para ser usado por pessoas sem deficiência visual. Ao divulgar essa fonte tipográfica, acredito que mais pessoas vão ficar familiarizadas com a linguagem”, acrescenta o designer. Ele afirma ainda que a fonte pode substituir as atuais sinalizações no espaço público, precisando apenas fazer alguns ajustes. “É fácil implementá-la na infraestrutura existente e é também um trampolim para um futuro mais sustentável e inclusivo após 2020”, comenta Takahashi.'
							
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
							}
							
							synth.cancel();
							reproduzirFrase(frasePrincipal);

							document.onkeyup = function(e) {
								if (e.which == 96) {
									reproduzirFrase(frasePrincipal);
								} else if (e.which == 97) {
									synth.cancel();
									$state.go('principal', {}, {
										reload : true
									});
								} else if (e.which == 98) {
									synth.cancel();
									$state.go('contato', {}, {
										reload : true
									});
								} else if (e.which == 99) {
									synth.cancel();
									$state.go('localizacao', {}, {
										reload : true
									});
								} else if (e.which == 100) {
									synth.cancel();
									$state.go('login', {}, {
										reload : true
									});
								} else if (e.which == 105) {
									reproduzirFrase(fraseAjuda);
								}
							};

							comecarReconhecimento();
							
							recognition.onresult = function(event) {
								for (let i = event.resultIndex; i < event.results.length; i++) {
									if (event.results[i].isFinal) {
										var son = event.results[i][0].transcript
												.trim();
										console.log(son);
										if (son == 'principal') {
											synth.cancel();
											$state.go('principal', {}, {
												reload : true
											});
										} else if (son == 'contato') {
											synth.cancel();
											$state.go('contato', {}, {
												reload : true
											});
										} else if (son == 'localização') {
											synth.cancel();
											$state.go('localizacao', {}, {
												reload : true
											});
										} else if (son == 'localização') {
											synth.cancel();
											$state.go('localizacao', {}, {
												reload : true
											});
										} else if (son == 'login') {
											synth.cancel();
											$state.go('login', {}, {
												reload : true
											});
										}else if (son == 'notícia') {
											document.getElementById("noticia01").focus();
											reproduzirFrase(noticia);
										} else if (son == 'outros' || son == 'ajuda') {
											reproduzirFrase(fraseAjuda);
										} else if (son == 'repetir') {
											reproduzirFrase(frasePrincipal);
										} else {
											reproduzirFrase('Descupa não entendi, por favor repita. Em caso de dúvida diga ajuda.');
										}
									}
								}
							};


						} ]);
