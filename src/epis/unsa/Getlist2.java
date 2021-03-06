package epis.unsa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Noticia;

	@SuppressWarnings("serial")
	public class Getlist2 extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			//resp.setContentType("text/plain");
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			final PersistenceManager pm = PMF.get().getPersistenceManager();
			final Query q = pm.newQuery(Noticia.class);
			
			if( req.getParameter("autor")!=null ){
				
				String autor = req.getParameter("autor");
				//q.setOrdering("key ascending");
				q.setOrdering("key descending");
				q.setRange(0, 10);
				q.setFilter("autor == colorParam");
				q.declareParameters("String colorParam");
				
				try{
					
					@SuppressWarnings("unchecked")
					List<Noticia> noticias = (List<Noticia>) q.execute(autor);
					req.setAttribute("noticias", noticias);
					RequestDispatcher rd=req.getRequestDispatcher("WEB-INF/jsp/noticias.jsp");
					rd.include(req, resp);
				
				}catch(Exception e){
					System.out.println(e);
				}finally{
					q.closeAll();
					pm.close();
				}
				
			}else {
				q.setOrdering("key descending");
				q.setRange(0, 10);
				
						 
				try{
					@SuppressWarnings("unchecked")
					List<Noticia> noticias = (List<Noticia>) q.execute();
					if( noticias.isEmpty() ){
						pm.makePersistent(new Noticia("severus","MATRICULAS","ESTIMADOS ALUMNOS LAS MATRICULAS EMPEZARAN EL DIA LUNES 5 DE MARZO Y CONCLUYEN EL 12 DE MARZO."));
					}
					req.setAttribute("noticias", noticias);
					RequestDispatcher rd=req.getRequestDispatcher("WEB-INF/jsp/noticias.jsp");
					rd.include(req, resp);
				}catch(Exception e){
					System.out.println(e);
					RequestDispatcher rd=req.getRequestDispatcher("inicio.html");
					rd.include(req, resp);
				}finally{
					q.closeAll();
					pm.close();
				}
			}			
		}
	}

	