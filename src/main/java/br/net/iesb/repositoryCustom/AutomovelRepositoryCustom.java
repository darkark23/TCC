package br.net.iesb.repositoryCustom;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import br.net.iesb.entity.transacional.Automovel;
import br.net.iesb.json.AutomovelFiltroJson;
import br.net.iesb.repository.transacional.AutomovelRepository;

@org.springframework.stereotype.Repository
public class AutomovelRepositoryCustom {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AutomovelRepository automovelRepository;

	public List<Automovel> getListaAutomvelFiltro(AutomovelFiltroJson filtro) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer i = 1;

		try {
			connection = dataSource.getConnection();

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT * FROM AUTOMOVEL A INNER JOIN MODELO M ON A.ID_MODELO = M.ID WHERE 1=1 ");
			if (filtro.getPlaca() != null || filtro.getPlaca().equals("")) {
				stringBuilder.append("AND A.PLACA LIKE ? ");
			}
			if (filtro.getModelo() != null || filtro.getModelo().equals("")) {
				stringBuilder.append("AND m.DESCRICAO LIKE ? ");
			}
			if (filtro.getFabricante() != null) {
				stringBuilder.append("AND A.ID_FABRICANTE = ?");
			}
			if (filtro.getTracao() != null) {
				stringBuilder.append("AND A.ID_TRACAO = ?");
			}
			if (filtro.getCategoria() != null) {
				stringBuilder.append("AND A.ID_Categoria = ?");
			}

			preparedStatement = connection.prepareStatement(stringBuilder.toString());

			if (filtro.getPlaca() != null || filtro.getPlaca().equals("")) {
				preparedStatement.setString(i++, "%" + filtro.getPlaca() + "%");
			}
			if (filtro.getModelo() != null || filtro.getModelo().equals("")) {
				preparedStatement.setString(i++, "%" + filtro.getModelo() + "%");
			}
			if (filtro.getFabricante() != null) {
				preparedStatement.setInt(i++, filtro.getFabricante());
			}
			if (filtro.getTracao() != null) {
				preparedStatement.setInt(i++, filtro.getTracao());
			}
			if (filtro.getCategoria() != null) {
				preparedStatement.setInt(i++, filtro.getCategoria());
			}

			resultSet = preparedStatement.executeQuery();

			List<Automovel> listaAutomovel = new ArrayList<Automovel>();

			while (resultSet.next()) {
				if (automovelRepository.findById(resultSet.getInt(1)).isPresent()) {
					listaAutomovel.add(automovelRepository.findById(resultSet.getInt(1)).get());
				}

			}

			return listaAutomovel;
		} finally {
			closeConnectionResources(connection, preparedStatement, null, resultSet);
		}

	}

	private void closeConnectionResources(Connection connection, PreparedStatement preparedStatement,
			CallableStatement callableStatement, ResultSet resultSet) throws SQLException {

		if (resultSet != null) {
			resultSet.close();
		}

		if (preparedStatement != null) {
			preparedStatement.close();
		}

		if (callableStatement != null) {
			callableStatement.close();
		}

		if (connection != null) {
			connection.close();
		}

	}

	
	
}
