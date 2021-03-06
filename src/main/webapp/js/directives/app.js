'use strict'
angular.module('tccApp', [ 'ui.router','restangular','ui.mask','ngToast','ngMaterial','ngMessages','ui.select','ngSanitize','ngMask']).config(
		[ '$stateProvider', '$urlRouterProvider','$locationProvider', 'RestangularProvider','$mdDateLocaleProvider',
				function($stateProvider, $urlRouterProvider, $locationProvider, RestangularProvider,$mdDateLocaleProvider) {
			
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
						url : '/agenda/?data',
					        views: {
					            '': {
									templateUrl : '/js/directives/agenda/agenda.html',
									controller : 'AgendaController'
					            },
					            'headNav': {
					            	templateUrl: '/js/components/head-menu.html',
					            	controller: 'AgendaController'
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
				            	controller: 'AudioController'
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
				            	controller: 'AudioBuscaController'
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
								controller: 'AudioBuscaControllerAvancada'
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
				            	controller: 'AudioListaController'
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
				            	controller: 'LoginController'
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
				            	controller: 'MenuController'
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
				            	controller: 'PrincipalController'
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
				            	controller: 'LocalizacaoController'
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
				            	controller: 'ContatoController'
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
								templateUrl: '/js/components/head-menu.html',
								controller: 'MenuController'
							}
						}
					}).state('aulaLedor', {
						url : '/aulaLedor/?data',
						views: {
							'': {
								templateUrl : '/js/directives/paginas-ledor/ledor-aulas/ledor-aulas.html',
								controller : 'LedorAulasController'
							},
							'headNav': {
								templateUrl: '/js/components/head-menu.html',
								controller: 'MenuController'
							}
						}
					}).state('audioEditarLedor', {
						url : '/audioEditarLedor/?id',
						views: {
							'': {

								templateUrl : '/js/directives/paginas-ledor/ledor-editar-audio/ledor-editar-audio.html',
								controller : 'LedorEditarAudioController'
							},
							'headNav': {
								templateUrl: '/js/components/head-menu.html',
								controller: 'MenuController'
							}
						}
					}).state('adminListaUsuario', {
						url : '/adminListaUsuario',
						views: {
							'': {
								templateUrl : '/js/directives/paginas-admin/admin-lista-usuario/admin-lista-usuario.html',
								controller : 'AdminListaUsuarioController'
							},
							'headNav': {
								templateUrl: '/js/components/head-menu.html',
								controller: 'MenuController'
							}
						}
					}).state('usuarioEdicao', {
						url : '/usuarioEdicao/?id/?indicadorNovo/?indicadorChave',
						views: {
							'': {
								templateUrl : '/js/directives/usuario-edicao/usuario-edicao.html',
								controller : 'UsuarioEdicaoController'
							},
							'headNav': {
								templateUrl: '/js/components/head-menu.html',
								controller: 'MenuController'
							}
						}
					}).state('geradorChaveLedor', {
						url : '/geradorChaveLedor',
						views: {
							'': {
								templateUrl : '/js/directives/geracao-chave-ledor/geracao-chave-ledor.html',
								controller : 'GeracaoChaveLedorController'
							},
							'headNav': {
								templateUrl: '/js/components/head-menu.html',
								controller: 'MenuController'
							}
						}
					});
									
					$locationProvider.hashPrefix('');
					RestangularProvider.setBaseUrl('');
					$mdDateLocaleProvider.shortMonths  = ['Jan', 'Fev', 'Mar', 'Abril','Maio', 'Jun', 'Jul','Ago', 'Set', 'Out','Nov','Dez'];
					$mdDateLocaleProvider.Months  = ['Janeiro', 'Fevereiro', 'Março', 'Abril','Maio', 'Junho', 'Julho','Agosto', 'Setembro', 'Outubro','Novembro','Dezembro'];
					$mdDateLocaleProvider.days = ['Domingo','Segunda', 'Terça', 'Quarta', 'Quinta','Sexta', 'Sabado'];
					$mdDateLocaleProvider.shortDays = ['D', 'S', 'T', 'Q', 'Q','S','S'];
					$mdDateLocaleProvider.formatDate = function(date) {
						return moment(date).format('DD/MM/YYYY');
					};
					$mdDateLocaleProvider.parseDate = function (dateString) {
						var m = moment(dateString, 'DD/MM/YYYY', true);
						return m.isValid() ? m.toDate() : new Date(NaN);
					}
				}]).run(function ($rootScope) {

				$rootScope.keyDown = function(value){
					if(value.keyCode == 18 ) {
						ativarSuporte();
					}
				};
});
