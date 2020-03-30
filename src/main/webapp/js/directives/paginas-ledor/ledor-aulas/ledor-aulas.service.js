'use strict'
angular.module('tccApp').service('ledorAulasService', ['Restangular', function (Restangular) {

    this.api = function () {
        return Restangular.one('aula');
    };

    this.agendaDiaEdicao = function (dados ,success, error) {
        return Restangular.all('aula/agendaDiaEdicao').post(dados).then(success, error);
    };

    this.cancelarAula = function (id ,success, error) {
        return Restangular.all('aula/cancelarAula').post(id).then(success, error);
    };

    this.rejeitarAula = function (motivo ,success, error) {
        return Restangular.all('aula/rejeitarAula').post(motivo).then(success, error);
    };

    this.aprovarAula = function (id ,success, error) {
        return Restangular.all('aula/aprovarAula').post(id).then(success, error);
    };

    this.getListaAssunto = function (success, error) {
        var lista = this.api().one('getListaAssunto').get().then(success, error);
        return lista;
    }

    this.saveAula = function (aula ,success, error) {
        return Restangular.all('aula/saveAula').post(aula).then(success, error);
    };

}]);
