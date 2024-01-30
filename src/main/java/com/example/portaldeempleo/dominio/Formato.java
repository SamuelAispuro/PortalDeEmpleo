package com.example.portaldeempleo.dominio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.portaldeempleo.entities.Usuario;
import com.example.portaldeempleo.entities.Vacante;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Paragraph;

public class Formato {

	Document documento;
	FileOutputStream fileOutputStream;
	
	// fuente del documento
	Font fuenteTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN,16);
	Font fuenteParrafo = FontFactory.getFont(FontFactory.HELVETICA,12);
	
	//metodo para crear el documento
	public void crearDocumento() throws FileNotFoundException, DocumentException {
		//tamaño de hoja y margenes L,R,T,B
		documento = new Document(PageSize.A1,20,20,50,50);
		
		//ruta archivo que vamos a generar
		String ruta = System.getProperty("user.home");
		fileOutputStream = new FileOutputStream(ruta + "/Desktop/Reportes/reporteJava.pdf");
		PdfWriter.getInstance(documento, fileOutputStream);
		
	}
	
	public void abrirDocumento() {
		documento.open();
	}
	
	// Agregar un titulo
	public void agregarTitulo(String titulo) throws DocumentException {
		//se coloca el numero de columnas 
		PdfPTable tabla = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase(titulo,fuenteTitulo));
		celda.setColspan(5);
		celda.setBorderColor(BaseColor.WHITE);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla.addCell(celda); 
		documento.add(tabla);
	}
	
	
	// Agregar un parrafo
	public void agregarParrafo(String texto) throws DocumentException {
		Paragraph parrafo  = new Paragraph();
		parrafo.add(new Phrase(texto,fuenteParrafo));
		documento.add(parrafo);
	}
	
	// AAgregar saltos de linea
	public void agregarSaltoDeLinea() throws DocumentException {
		Paragraph saltos_deLinea = new Paragraph();
		saltos_deLinea.add(new Phrase(Chunk.NEWLINE));
		saltos_deLinea.add(new Phrase(Chunk.NEWLINE));
		documento.add(saltos_deLinea);
	}
	
	public void agregarTablaVacantes(List<Vacante> vacantes) throws DocumentException {
		PdfPTable tabla = new PdfPTable(9);
		tabla.addCell("Id");
		tabla.addCell("Nombre");
		tabla.addCell("Empresa");
		tabla.addCell("Sueldo");
		tabla.addCell("Horario");
		tabla.addCell("Modalidad");
		tabla.addCell("Contratación");
		tabla.addCell("Estado");
		tabla.addCell("Estatus");
		
		for(Vacante vacante : vacantes) {
			tabla.addCell(String.valueOf(vacante.getId_vacante()));
			tabla.addCell(vacante.getNombreVacante());
			tabla.addCell(vacante.getEmpresa().getNombre());
			tabla.addCell(String.valueOf(vacante.getSueldo()));
			tabla.addCell(String.valueOf(vacante.getHorario()));
			tabla.addCell(vacante.getModalidadTrabajo().getModalidad());
			tabla.addCell(vacante.getTipoContratacion().getHorario());
			tabla.addCell(vacante.getEstado().getNombreEstado());
			
			if(vacante.getEstatus()) {
				tabla.addCell("Activa");
			} else {
				tabla.addCell("Inactiva");
			}
			
		}
		
		documento.add(tabla);
	}
	
	
	// Agregar tabla
	public void agregarTablaUsuarios(List<Usuario> usuarios) throws DocumentException {
		PdfPTable tabla = new PdfPTable(8);
		tabla.addCell("Id");
		tabla.addCell("Nombre");
		tabla.addCell("Apellido Paterno");
		tabla.addCell("Apellido Materno");
		tabla.addCell("Correo Electronico");
		tabla.addCell("Telefono");
		tabla.addCell("Rol");
		tabla.addCell("Estatus");
		
		for(Usuario usuario : usuarios) {
			tabla.addCell(String.valueOf(usuario.getId_usuario()));
			tabla.addCell(String.valueOf(usuario.getNombre()));
			tabla.addCell(String.valueOf(usuario.getApellidoP()));
			tabla.addCell(String.valueOf(usuario.getApellidoM()));
			tabla.addCell(String.valueOf(usuario.getCorreoElectronico()));
			tabla.addCell(String.valueOf(usuario.getTelefono()));
			
			if(usuario.getTipoUsuario()==1) {
				tabla.addCell("Administrador");
			} else if (usuario.getTipoUsuario()==2){
				tabla.addCell("Candidato");
			} else if (usuario.getTipoUsuario()==3) {
				tabla.addCell("Empleador");
			}
			
			if(usuario.getEstatusUsuario()) {
				tabla.addCell("Activo");
			}else {
				tabla.addCell("Suspendido");
			}
		}
		
		documento.add(tabla);
	}
	
	public void cerrarDocumento() {
		documento.close();
	}
}
