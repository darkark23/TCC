'use strict'
angular
		.module('tccApp')
		.controller(
				'CadastroController',
				[
						'$scope',
						'$state',
						'$stateParams',
						'cadastroService','ngToast', '$rootScope',
						function($scope, $state, $stateParams, cadastroService, ngToast, $rootScope) {

							$state.status = null;
							$rootScope.nav = 1;
							if (!$state.params.id) {
								$scope.objetoAutomovel = {
									id : null,
									placa : null,
									modelo : null,
									fabricante : null,
									tracao : null,
									categoria : null
								};
							} else {
								cadastroService.getAutomovel($state.params.id,
												function(automovel) {
													$scope.objetoAutomovel = {
														id : automovel.id,
														placa : automovel.placa,
														modelo : automovel.modelo,
														fabricante : automovel.fabricante,
														tracao : automovel.tracao,
														categoria : automovel.categoria
													};
													$scope
															.selecionarFabricante(automovel.fabricante);
												}, function() {
												});
							}

							$scope.filtroFabricante = {
								nome : null,
								pais : null
							}

							$scope.acessarConsulta = function(id) {
								$state.go('consulta', {}, {
									reload : true
								});
							};

							cadastroService.getListaCategoria(function(
									retornoLista) {
								$scope.listaCategoria = retornoLista;
							}, function() {
							});

							cadastroService.getListaPais(
									function(retornoLista) {
										$scope.listaPais = retornoLista;
									}, function() {
									});
							
							cadastroService.getListaModelo(function(
									retornoLista) {
								$scope.listaModelo = retornoLista;
							}, function() {
							});

							cadastroService
									.getListaFabricanteView(
											$scope.filtroFabricante,
											function(retornoLista) {
												$scope.listaFabricanteView = retornoLista;
											}, function() {
											});

							$scope.atualizarTabelaFabricante = function() {
								cadastroService
										.getListaFabricanteView(
												$scope.filtroFabricante,
												function(retornoLista) {
													$scope.listaFabricanteView = retornoLista;
												}, function() {
												});
							};

							$scope.selecionarFabricante = function(id) {
								cadastroService
										.getFabricante(
												id,
												function(fabricante) {
													$scope.fabricanteTela = fabricante;
													$scope.objetoAutomovel.fabricante = fabricante.id;
												}, function() {
												});
							};

							$scope.limparFabricante = function() {
								$scope.filtroFabricante = {
									nome : null,
									pais : null,
								}
								$scope.atualizarTabelaFabricante();
							};

							$scope.autoComplete = function(string) {
								if (string == '' || string == null) {
									$scope.hidethis = true;
								} else {
									$scope.hidethis = false;
									var listaModeloAutoComplete = [];
									angular
											.forEach(
													$scope.listaModelo,
													function(modelo) {
														if (modelo.descricao
																.toLowerCase()
																.indexOf(
																		string
																				.toLowerCase()) >= 0) {
															listaModeloAutoComplete
																	.push(modelo)
														}
													});
									$scope.listaModeloAutoComplete = listaModeloAutoComplete;
								}
							};

							$scope.fillTextbox = function(modelo) {
								$scope.objetoAutomovel.modelo = modelo;
								$scope.hidethis = true;
							};
							
							$scope.salvarAutomovel =  function() {
														
							cadastroService.salvarAutomovel($scope.objetoAutomovel,
									function(status) {
									$scope.status = status;
									if(status == 0){
										$scope.status = $scope.objetoAutomovel;
										$scope.acessarConsulta();
										if(!$state.params.id){
											ngToast.create('Carro adicionado com sucesso.');
										}else {
											ngToast.create('Carro alterado com sucesso.');
										}
										
									} else {
										if (status == 1){
											ngToast.create('Informe todos os campos do formulario.');
										} else if (status == 2){
											ngToast.create('Formato da placa incorreta.');
										} else if (status == 3 || status == 4){
											ngToast.create('A placa informada já esta sendo utilizada.');
										} else if (status == 5){
											ngToast.create('O carro informado não exite.');
										} else{
											ngToast.create('Erro desconhecido.');
										} 
									}
									
									}, function() {
										ngToast.create('Erro desconhecido.');
									});
								
							};
							
							$scope.salvarFabricante =  function() {
								cadastroService.salvarFabricante($scope.filtroFabricante,
									function(id) {
										$scope.selecionarFabricante(id);
										$scope.limparFabricante();
										ngToast.create('Fabricante inserido com sucesso.');
									}, function() {
									});
							};
							
						} ]);
