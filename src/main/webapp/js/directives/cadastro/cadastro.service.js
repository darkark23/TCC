'use strict'
angular.module('tccApp').service('cadastroService', ['Restangular', function (Restangular) {
	
    this.api = function () {
        return Restangular.one('cadastroService');
    };
    
    this.getLista = function (filtro,success, error) {
        var lista = this.api().one('getLista').withHttpConfig({paramSerializer: '$httpParamSerializerJQLike'}).customGET('', filtro).then(success, error);
        return lista;
    };
    
    this.getFabricante = function (id, success, error) {
        var fabricante = this.api().one('getFabricante/' + id).get().then(success, error);
        return fabricante;
    };
    
    this.getAutomovel = function (id, success, error) {
        var automovel = this.api().one('getAutomovel/' + id).get().then(success, error);
        return automovel;
    };
    
    this.removerAutomovel = function (id, success, error) {
        var status = this.api().one('removerAutomovel/' + id).get().then(success, error);
        return status;
    };
    
    this.getListaCategoria = function (success, error) {
        var lista = this.api().one('getListaCategoria').get().then(success, error);
        return lista;
    };
        
    this.getListaFabricanteView = function (filtro,success, error) {
        var lista = this.api().one('getListaFabricanteView').withHttpConfig({paramSerializer: '$httpParamSerializerJQLike'}).customGET('', filtro).then(success, error);
        return lista;
    };
    
    this.getListaFabricante = function (success, error) {
        var lista = this.api().one('getListaFabricante').get().then(success, error);
        return lista;
    };
    
    this.getListaTracao = function (success, error) {
        var lista = this.api().one('getListaTracao').get().then(success, error);
        return lista;
    };
    
    this.getListaPais = function (success, error) {
        var lista = this.api().one('getListaPais').get().then(success, error);
        return lista;
    };

    this.getListaModelo = function (success, error) {
        var lista = this.api().one('getListaModelo').get().then(success, error);
        return lista;
    };
    
    this.getListaModeloAutoCompleteView = function (success, error) {
        var lista = this.api().one('getListaModeloAutoCompleteView').get().then(success, error);
        return lista;
    };
    
    this.salvarAutomovel = function (automovelView,success, error) {
        var status = this.api().one('salvarAutomovel').withHttpConfig({paramSerializer: '$httpParamSerializerJQLike'}).customGET('', automovelView).then(success, error);
        return status;
    };
    
    this.salvarFabricante = function (fabricanteView,success, error) {
        var id = this.api().one('salvarFabricante').withHttpConfig({paramSerializer: '$httpParamSerializerJQLike'}).customGET('', fabricanteView).then(success, error);
        return id;
    };
    	
}]);
