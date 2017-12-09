package cs2410;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageManager {
    public static ImageView emptyImageView() { return sizeImage(new ImageView(new Image(new File("images/empty.png").toURI().toString()))); }
    public static ImageView flagImageView() { return sizeImage(new ImageView(new Image(new File("images/flag.png").toURI().toString()))); }
    public static ImageView questionImageView() { return sizeImage(new ImageView(new Image(new File("images/question.png").toURI().toString()))); }
    public static ImageView bombImageView() { return sizeImage(new ImageView(new Image(new File("images/bomb.png").toURI().toString()))); }
    public static ImageView oneImageView() { return sizeImage(new ImageView(new Image(new File("images/one.png").toURI().toString()))); }
    public static ImageView twoImageView() { return sizeImage(new ImageView(new Image(new File("images/two.png").toURI().toString()))); }
    public static ImageView threeImageView() { return sizeImage(new ImageView(new Image(new File("images/three.png").toURI().toString()))); }
    public static ImageView fourImageView() { return sizeImage(new ImageView(new Image(new File("images/four.png").toURI().toString()))); }
    public static ImageView fiveImageView() { return sizeImage(new ImageView(new Image(new File("images/five.png").toURI().toString()))); }
    public static ImageView sixImageView() { return sizeImage(new ImageView(new Image(new File("images/six.png").toURI().toString()))); }
    public static ImageView sevenImageView() { return sizeImage(new ImageView(new Image(new File("images/seven.png").toURI().toString()))); }
    public static ImageView eightImageView() { return sizeImage(new ImageView(new Image(new File("images/eight.png").toURI().toString()))); }

    private static ImageView sizeImage(ImageView imageView) {
        imageView.setFitHeight(10);
        imageView.setFitWidth(10);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
