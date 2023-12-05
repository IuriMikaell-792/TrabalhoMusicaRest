package org.libertas.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MusicaDAO {

	public void inserir(Musica m) {
		Conexao con = new Conexao();
		try {
			String sql = "INSERT INTO cad_musica "
					+ "(nome, cantor, genero) VALUES (?, ?, ?);";
			PreparedStatement prep = con.getConnection().prepareStatement(sql);
			prep.setString(1, m.getNome());
			prep.setString(2, m.getCantor());
			prep.setString(3, m.getGenero());
			prep.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
	}
	
	public void alterar(Musica m) {
		Conexao con = new Conexao();
		try {
			String sql = "UPDATE cad_musica "
					+ "SET nome = ?, cantor = ?, genero = ? "
					+ "WHERE idmusica = ?";
			PreparedStatement prep = con.getConnection().prepareStatement(sql);
			prep.setString(1, m.getNome());
			prep.setString(2, m.getCantor());
			prep.setString(3, m.getGenero());
			prep.setInt(4, m.getIdmusica());
			prep.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
	}

	public void excluir(Musica m) {
		Conexao con = new Conexao();
		try {
			String sql = "DELETE FROM cad_musica "
					+ "WHERE idmusica = ?;";
			PreparedStatement prep = con.getConnection().prepareStatement(sql);
			prep.setInt(1, m.getIdmusica());
			prep.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
	}
	
	public Musica consultar(int id) {
		Musica m = new Musica();
		Conexao con = new Conexao();
		try {
			String sql = "SELECT * FROM cad_musica WHERE idmusica = " + id;
			Statement sta = con.getConnection().createStatement();
			ResultSet res = sta.executeQuery(sql);
			if (res.next()) {
				m.setIdmusica(res.getInt("idmusica"));
				m.setNome(res.getString("nome"));
				m.setCantor(res.getString("cantor"));
				m.setGenero(res.getString("genero"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		return m;
	}
	
	public List<Musica> listar() {
		List<Musica> lista = new LinkedList<Musica>();
		Conexao con = new Conexao();
		try {
			String sql = "SELECT * FROM cad_musica ORDER BY nome";
			Statement sta = con.getConnection().createStatement();
			ResultSet res = sta.executeQuery(sql);
			while (res.next()) {
				Musica m = new Musica();
				m.setIdmusica(res.getInt("idmusica"));
				m.setNome(res.getString("nome"));
				m.setCantor(res.getString("cantor"));
				m.setGenero(res.getString("genero"));
				lista.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		return lista;
	}
}
