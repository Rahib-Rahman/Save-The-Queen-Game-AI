
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 *
 * @author Sheikh Md. Rahibur Rahman
 * 
 *
 */

public class SaveTheQueen {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame("Save The Queen");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new DrawCanvas());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

class DrawCanvas extends JPanel implements ActionListener {

    Timer t = new Timer(1000, this);

    int ROWS = 5;
    int COLS = 5;
    int IMAGE_SIZE = 50;
    int PADDING = 20;
    int CELL_SIZE = IMAGE_SIZE + 2 * PADDING;
    int CANVAS_SIZE = CELL_SIZE * ROWS;
    int rowDust, colDust, rowVaccumCleaner = 0, colVaccumCleaner = 0, leftToRight = 1;
    int[][] dustInput = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

    JButton btn;
    Image imgVaccumCleaner;
    Image imgDust;

    public DrawCanvas() {
        imgVaccumCleaner = new ImageIcon(getClass().getClassLoader().getResource("images/robot.png")).getImage();
        imgDust = new ImageIcon(getClass().getClassLoader().getResource("images/queen.png")).getImage();

        btn = new JButton("Save Her!");
        add(btn);

        btn.addActionListener((ActionEvent e) -> {
            t.start();
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(CANVAS_SIZE, CANVAS_SIZE + 55);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        // create vertical grid lines
        for (int i = 0; i <= ROWS; i++) {
            g.fill3DRect(0, CELL_SIZE * i + 50, CELL_SIZE * ROWS, 4, true);
        }

        // create horizontal grid lines
        for (int i = 0; i <= COLS; i++) {
            g.fill3DRect(CELL_SIZE * i - 2, 50, 4, CELL_SIZE * COLS, true);
        }

        // create dusts corresponding to input
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (dustInput[i][j] == 1) {
                    g.drawImage(imgDust, CELL_SIZE * i + PADDING, CELL_SIZE * j + PADDING + 50, IMAGE_SIZE, IMAGE_SIZE, this);
                }
            }
        }

        // create vaccum cleaner
        g.drawImage(imgVaccumCleaner, CELL_SIZE * colVaccumCleaner + PADDING, CELL_SIZE * rowVaccumCleaner + PADDING + 50, IMAGE_SIZE, IMAGE_SIZE, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!(rowVaccumCleaner == 3 && colVaccumCleaner == 2)) {
            if (rowVaccumCleaner < 3) {
                rowVaccumCleaner++;
            } else if (colVaccumCleaner < 2) {
                colVaccumCleaner++;
            }

            if (dustInput[rowVaccumCleaner][colVaccumCleaner] == 1) {
                dustInput[rowVaccumCleaner][colVaccumCleaner] = 0;
            }

            repaint();
        }

        /*
        if (!(colVaccumCleaner == 4 && rowVaccumCleaner == 4)) {
            if (leftToRight == 1) {
                if (colVaccumCleaner < 4) {
                    colVaccumCleaner++;
                } else if (colVaccumCleaner == 4) {
                    rowVaccumCleaner++;
                    leftToRight = 0;
                }
            } else if (colVaccumCleaner > 0) {
                colVaccumCleaner--;
            } else if (colVaccumCleaner == 0) {
                rowVaccumCleaner++;
                leftToRight = 1;
            }

            if (dustInput[colVaccumCleaner][rowVaccumCleaner] == 1) {
                dustInput[colVaccumCleaner][rowVaccumCleaner] = 0;
            }

            repaint();

        }*/
    }
}
