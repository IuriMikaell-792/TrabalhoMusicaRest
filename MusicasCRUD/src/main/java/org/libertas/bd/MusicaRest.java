package org.libertas.bd;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

/**
 * Servlet implementation class MusicaRest
 */
public class MusicaRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MusicaRest() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		MusicaDAO mdao = new MusicaDAO();
		List<Musica> lista = mdao.listar();
		Gson gson = new Gson();
		out.print(gson.toJson(lista));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String body = sb.toString();
			
			Gson gson = new Gson();
			Musica m = gson.fromJson(body, Musica.class);
			
			MusicaDAO mdao = new MusicaDAO();
			mdao.inserir(m);
			
			Retorno r = new Retorno(true, "Registro inserido com sucesso");
			out.print(gson.toJson(r));
		} catch (Exception e) {
			e.printStackTrace();
			Gson gson = new Gson();
			Retorno r = new Retorno(false, "Erro ao inserir registro");
			out.print(gson.toJson(r));
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();	
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String body = sb.toString();
			
			Gson gson = new Gson();
			Musica m = gson.fromJson(body, Musica.class);
			
			MusicaDAO mdao = new MusicaDAO();
			mdao.alterar(m);

			Retorno r = new Retorno(true, "Registro alterado com sucesso");
			out.print(gson.toJson(r));
		} catch (Exception e) {
			e.printStackTrace();
			Gson gson = new Gson();
			Retorno r = new Retorno(false, "Erro ao alterar registro");
			out.print(gson.toJson(r));
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String id = request.getRequestURI();
			id = id.substring(id.lastIndexOf("/") + 1);
			
			MusicaDAO mdao = new MusicaDAO();
			Musica m = new Musica();
			m.setIdmusica(Integer.parseInt(id));
			mdao.excluir(m);
			
			Retorno r = new Retorno(true, "Registro excluido com sucesso");
			Gson gson = new Gson();
			out.print(gson.toJson(r));
		} catch (Exception e) {
			e.printStackTrace();
			Gson gson = new Gson();
			Retorno r = new Retorno(false, "Erro ao excluir registro");
			out.print(gson.toJson(r));
		}
	}
}
