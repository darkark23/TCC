'use strict'
angular.module('tccApp', [ 'ui.router','restangular','ui.mask','ngToast','ngMaterial','ngMessages']).config(
		[ '$stateProvider', '$urlRouterProvider','$locationProvider', 'RestangularProvider',
				function($stateProvider, $urlRouterProvider, $locationProvider, RestangularProvider) {
			
					$urlRouterProvider.otherwise('principal');
					$stateProvider.state('home', {
						url : '/home',
						templateUrl : '/js/directives/home/home.html',
						controller : 'HomeController'
					}).state('cadastro', {
						url : '/carro/:id',
						templateUrl : '/js/directives/cadastro/cadastro.html',
						controller : 'CadastroController'
					}).state('consulta', {
						url : '/carros',
						templateUrl : '/js/directives/consulta/consulta.html',
						controller : 'ConsultaController'
					}).state('agenda', {
						url : '/agenda/:data',
					        views: {
					            '': {
									templateUrl : '/js/directives/agenda/agenda.html',
									controller : 'AgendaController'
					            },
					            'headNav': {
					            	templateUrl: '/js/components/head-menu.html',
					            	controller: 'AgendaController',
					            	}
					        }
					}).state('audio', {
						url : '/audio/:id',
				        views: {
				            '': {
								templateUrl : '/js/directives/audio/audio.html',
								controller : 'AudioController'
				            },
				            'headNav': {
				            	templateUrl: '/js/components/head-menu.html',
				            	controller: 'AudioController',
				            	}
				        }
					}).state('audioBusca', {
						url : '/audioBusca',
				        views: {
				            '': {
								templateUrl : '/js/directives/audio-busca/audio-busca.html',
								controller : 'AudioBuscaController'
				            },
				            'headNav': {
				            	templateUrl: '/js/components/head-menu.html',
				            	controller: 'AudioBuscaController',
				            	}
				        }
					}).state('audioBuscaAvancada', {
						url : '/audioBuscaAvancada',
						views: {
							'': {
								templateUrl : '/js/directives/audio-busca-avancada/audio-busca-avancada.html',
								controller : 'AudioBuscaControllerAvancada'
							},
							'headNav': {
								templateUrl: '/js/components/head-menu.html',
								controller: 'AudioBuscaControllerAvancada',
							}
						}
					}).state('audioLista', {
						url : '/audioLista/:termo',
				        views: {
				            '': {
								templateUrl : '/js/directives/audio-lista/audio-lista.html',
								controller : 'AudioListaController'
				            },
				            'headNav': {
				            	templateUrl: '/js/components/head-menu.html',
				            	controller: 'AudioListaController',
				            	}
				        }
					}).state('login', {
						url : '/login',
				        views: {
				            '': {
								templateUrl : '/js/directives/login/login.html',
								controller : 'LoginController'
				            },
				            'headNav': {
				            	templateUrl: '/js/components/head-principal.html',
				            	controller: 'LoginController',
				            	}
				        }
					}).state('menu', {
						url : '/menu',
				        views: {
				            '': {
								templateUrl : '/js/directives/menu/menu.html',
								controller : 'MenuController'
				            },
				            'headNav': {
				            	templateUrl: '/js/components/head-menu.html',
				            	controller: 'MenuController',
				            	}
				        }
					}).state('principal', {
						url: '/principal',
				        views: {
				            '': {
				            	templateUrl : '/js/directives/principal/principal.html',
								controller : 'PrincipalController'
				            },
				            'headNav': {
				            	templateUrl: '/js/components/head-principal.html',
				            	controller: 'PrincipalController',
				            	}
				        }
					}).state('localizacao', {
						url : '/localizacao',
				        views: {
				            '': {
								templateUrl : '/js/directives/localizacao/localizacao.html',
								controller : 'LocalizacaoController'
				            },
				            'headNav': {
				            	templateUrl: '/js/components/head-principal.html',
				            	controller: 'LocalizacaoController',
				            	}
				        }
					}).state('contato', {
						url : '/contato',
				        views: {
				            '': {
								templateUrl : '/js/directives/contato/contato.html',
								controller : 'ContatoController'
				            },
				            'headNav': {
				            	templateUrl: '/js/components/head-principal.html',
				            	controller: 'ContatoController',
				            	}
				        }
					}).state('audioListaLedor', {
						url : '/audioListaLedor',
						views: {
							'': {
								templateUrl : '/js/directives/paginas-ledor/ledor-lista-audio/ledor-lista-audio.html',
								controller : 'LedorListaAudioController'
							},
							'headNav': {
								templateUrl: '/js/components/head-menu-ledor.html',
								controller: 'MenuController',
							}
						}
					}).state('aulaLedor', {
						url : '/aulaLedor',
						views: {
							'': {
								templateUrl : '/js/directives/paginas-ledor/ledor-aulas/ledor-aulas.html',
								controller : 'LedorAulasController'
							},
							'headNav': {
								templateUrl: '/js/components/head-menu-ledor.html',
								controller: 'MenuController',
							}
						}
					});
									
					$locationProvider.hashPrefix('');
					RestangularProvider.setBaseUrl('');
				}]).run(function($rootScope) {
				    $rootScope.nav = null;
				});
