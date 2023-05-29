/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SonidoEImagen;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author a22victorlr
 */
public class Imagen {
     private static final String extension = ".png"; // extensión de la imagen
  /**
   * 
   * @param nombreImagen
   * @return devuelve el string a la direccicion de la imagen
   */
  public static String getImageLink(String nombreImagen) {
    String direccion = "src/imagen/" + nombreImagen + extension; // la imagen debe estar en la carpeta "images" del proyecto
    return direccion;
  }
  
  public static ImageIcon redimensionarimagen(ImageIcon imagen, int tamX, int tamY){
      Image imagenRedimensionada = imagen.getImage().getScaledInstance(tamX, tamY, Image.SCALE_DEFAULT);
      ImageIcon imagenNueva = new ImageIcon(imagenRedimensionada);
         return imagenNueva;
  }

  /* Para añadir una imagen a un objeto se haría asi
  
    ImageIcon miImagen = new ImageIcon("directorio");
    ImageIcon miImagenRedimensionada = Imagen.redimensionarimagen(miImagen, tamañoX, tamañoY);
    this.menuLabel.setIcon(miImagenRedimensionada);
  */
  
}
