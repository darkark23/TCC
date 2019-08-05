'use strict'
angular
		.module('tccApp')
		.controller(
				'ConsultaController',
				[
						"$scope",
						'$state',
						'consultaService',
						'$rootScope',
						function($scope, $state, consultaService, $rootScope) {

							$rootScope.nav = 2;
							$scope.fabricanteTela = null;
							$scope.registroExclusao = null;

							$scope.filtroAutomovel = {
								placa : null,
								modelo : null,
								fabricante : null,
								tracao : null,
								categoria : null
							};

							$scope.filtroFabricante = {
								nome : null,
								pais : null,
							}

							consultaService.getLista($scope.filtroAutomovel,
									function(retornoLista) {
										$scope.lista = retornoLista;
									}, function() {
									});

							consultaService.getListaCategoria(function(
									retornoLista) {
								$scope.listaCategoria = retornoLista;
							}, function() {
							});

							consultaService.getListaPais(
									function(retornoLista) {
										$scope.listaPais = retornoLista;
									}, function() {
									});

							consultaService.getListaModelo(function(
									retornoLista) {
								$scope.listaModelo = retornoLista;
							}, function() {
							});

							consultaService
									.getListaFabricanteView(
											$scope.filtroFabricante,
											function(retornoLista) {
												$scope.listaFabricanteView = retornoLista;
											}, function() {
											});

							$scope.atualizarTabela = function() {
								consultaService.getLista(
										$scope.filtroAutomovel, function(
												retornoLista) {
											$scope.lista = retornoLista;
										}, function() {
										});
							};

							$scope.atualizarTabelaFabricante = function() {
								consultaService
										.getListaFabricanteView(
												$scope.filtroFabricante,
												function(retornoLista) {
													$scope.listaFabricanteView = retornoLista;
												}, function() {
												});
							};

							$scope.limparPrincipal = function() {

								$scope.filtroAutomovel = {
									placa : null,
									modelo : null,
									fabricante : null,
									tracao : null,
									categoria : null
								};

								$scope.filtroFabricante = {
									nome : null,
									pais : null,
								}

								$scope.fabricanteTela = null;

								$scope.atualizarTabela();
								$scope.atualizarTabelaFabricante();
							};

							$scope.limparFabricante = function() {

								$scope.filtroFabricante = {
									nome : null,
									pais : null,
								}

								$scope.atualizarTabelaFabricante();
							};

							$scope.selecionarFabricante = function(id) {
								consultaService
										.getFabricante(
												id,
												function(fabricante) {
													$scope.fabricanteTela = fabricante;
													$scope.filtroAutomovel.fabricante = fabricante.id;
													$scope.atualizarTabela();
												}, function() {
												});
							};

							$scope.removerAutomovel = function(id) {
								consultaService.removerAutomovel(id, function(
										fabricante) {
									$scope.atualizarTabela();
								}, function() {
								});
							};

							$scope.setRegistroExclusao = function(id) {
								$scope.registroExclusao = id;
							};

							$scope.acessarAlterar = function(id) {
								$state.go('cadastro', {
									id : id
								}, {
									reload : true
								});
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
								$scope.filtroAutomovel.modelo = modelo;
								$scope.hidethis = true;
							};

						} ]);
