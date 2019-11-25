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

							var frasePrincipal = 'Página principal. Bem vindo ao Portal do Ledor. Diga notícia para escutar as últimas notícias. Diga outros para saber como acessar as outras páginas.';
							var noticia = "Notícia 01. Título. Designer cria fonte que une braile ao alfabeto tradicional. Publicado em 17 março de 2018, as 16 horas  e 21 minutos. São Paulo – Um designer japonês teve uma ideia brilhante para tentar incluir ainda mais os deficientes visuais no dia-a-dia da sociedade: criar uma fonte tipográfica que une a escrita tradicional ao braile. A lógica por trás disso é permitir que cegos e outras pessoas possam compartilhar de uma mesma comunicação em um espaço comum. Como? Da seguinte maneira: os pontos do braile aprecem em alto relevo e integrados aos caracteres tradicionais do alfabeto latino. O designer Kosuke Takahashi, criador da fonte Braile Neue, propõe que essa tipografia seja adotada já nos próximos Jogos Olímpicos, que ocorrem daqui a dois anos, em Tóquio, capital do Japão.	Mas há um problema: geralmente a comunicação em braile tende a ser pequena e quase invisível. Por isso, Takahashi diz que realizou uma pesquisa para ver se uma larga sinalização com a linguagem para deficientes visuais poderia ser legível a eles. “Descobri que é possível, contanto que exista um padrão de até seis pontos, independentemente do tamanho”, diz o designer, em seu website.“Hoje em dia, raramente vemos o braile implementado nos lugares públicos, uma vez que essa comunicação necessita de um espaço adicional, sendo considerada por aqueles que têm visão como algo não importante”, diz Takahashi.“A Braille Neue aborda essa questão, ao tornar o braile mais acessível para ser usado por pessoas sem deficiência visual. Ao divulgar essa fonte tipográfica, acredito que mais pessoas vão ficar familiarizadas com a linguagem”, acrescenta o designer. Ele afirma ainda que a fonte pode substituir as atuais sinalizações no espaço público, precisando apenas fazer alguns ajustes. “É fácil implementá-la na infraestrutura existente e é também um trampolim para um futuro mais sustentável e inclusivo após 2020”, comenta Takahashi."
							var currentNoticia = 0;
							$scope.noticias = null;


							principalService.getNoticias(function (listaNoticias) {
								$scope.noticias = listaNoticias;
							},function () {
								alert("Não foi possivel recuperar as notícias.")
							})

							synth.cancel();
							reproduzirFrase(frasePrincipal);

							comecarReconhecimento();

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
											currentNoticia = 1;
											reproduzirNoticia();
										} else if (son == 'outros' || son == 'ajuda') {
											reproduzirFrase(fraseAjuda);
										} else if (son == 'repetir' && currentNoticia != 0) {
											reproduzirNoticia();
										} else if (son == 'próxima' && currentNoticia != 0) {
											++currentNoticia;
											reproduzirNoticia();
										} else if (son == 'anterior' && currentNoticia != 0) {
											if(currentNoticia > 1){
												--currentNoticia;
												reproduzirNoticia();
											}
										} else {
											reproduzirFrase('Descupa não entendi, por favor repita. Em caso de dúvida diga ajuda.');
										}
									}
								}
							};

							function reproduzirNoticia () {
								if (!$scope.noticias){
									reproduzirFrase("Não existem notícias publicadas.");
									currentNoticia = 0;
								}else if(currentNoticia > $scope.noticias.length){
									reproduzirFrase("Todas as noticias foram lidas, digar anterior para voltar a noticia anterior ou diga noticia para começar a ler da primeira noticia.");
									--currentNoticia;
								}else if ($scope.noticias){
									document.getElementById("noticia"+currentNoticia).focus();
									var descricaoImagem = "";
									if($scope.noticias[currentNoticia - 1].descricaoImagem){
											descricaoImagem = "Descrição imagem: " + $scope.noticias[currentNoticia - 1].descricaoImagem;
									}

									reproduzirFrase(+ " Título: " + $scope.noticias[currentNoticia - 1].titulo
									+ " Imagem Notícia: " + descricaoImagem
									+ " Corpo: " + htmlToPlaintext($scope.noticias[currentNoticia- 1].corpo));
								} else {
									reproduzirFrase("Não existem notícias publicadas.");
									currentNoticia = 0;
								}
							}

							function htmlToPlaintext(text) {
								return text ? String(text).replace(/<[^>]+>/gm, '') : '';
							}

						} ]);
