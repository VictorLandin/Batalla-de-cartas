/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SonidoEImagen;

/**
 *
 * @author a22victorlr
 */
public class Imagen {
     private static final String IMAGE_EXTENSION = ".png"; // extensión de la imagen
  /**
   * 
   * @param nombreImagen
   * @return devuelve el string a la direccicion de la imagen
   */
  public static String getImageLink(String nombreImagen) {
    String direccion = "images/" + nombreImagen + IMAGE_EXTENSION; // la imagen debe estar en la carpeta "images" del proyecto
    return direccion;
  }
  /* Para añadir una imagen a un objeto se haría asi
  
    String imageName = "mi_imagen";
    String imageLink = ImageUtils.getImageLink(imageName);
    label.setIcon(new ImageIcon(imageLink));
  */
  
}
