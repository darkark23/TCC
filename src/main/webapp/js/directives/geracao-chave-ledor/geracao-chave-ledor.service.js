'use strict'
angular.module('tccApp').service('geracaoChaveLedorService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('chave');
    };

    this.gerarChaveCadastro = function (success, error) {
        return this.api().one('gerarChaveCadastro').get().then(success, error);
    };

    this.validarChaveCadastro = function (chave, success, error) {
        return this.api().one('validarChaveCadastro/'+ chave).get().then(success, error);
    };

}]);
