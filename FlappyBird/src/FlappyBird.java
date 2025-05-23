import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    // image attributes
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // Player
    int playerStartPosX = frameWidth/8;
    int playerStartPosY = frameHeight/2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    int score = 0;
    JLabel scoreLabel;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    Timer gameLoop;

    Timer pipesCooldown;

    int gravity = 1;

    boolean gameOver = false;

    // Constructor
    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);
        //setBackground(Color.blue);

        // load images
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        // player & score
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(scoreLabel, BorderLayout.NORTH);

        // pipa
        pipes = new ArrayList<Pipe>();
        pipesCooldown = new Timer(4000, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("pipa");
               placePipes();
           }
        });
        pipesCooldown.start();

        // mulai game
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/2;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for(int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), this);
        }
    }

    public void move(){
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for(int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            // cek apabila player melewati pipa
            if (!pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                if (pipe.getPosY() > 0) {
                    pipe.setPassed(true);
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
            }
        }
    }

    public void checkCollision() {
        // cek jika player keluar dari frame
        if (player.getPosY() + player.getHeight() > frameHeight) {
            endGame();
            return;
        }

        // cek jika player menabrak pipa
        for (Pipe pipe : pipes) {
            Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());

            if (playerRect.intersects(pipeRect)) {
                endGame();
                return;
            }
        }
    }

    public void endGame() {
        gameLoop.stop();
        pipesCooldown.stop();
        gameOver = true;
        JOptionPane.showMessageDialog(this, "Game Over! Tekan 'R' untuk restart.", "Flappy Bird", JOptionPane.INFORMATION_MESSAGE);
    }

    public void restartGame() {
        // reset posisi player
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
        gameOver = false; // reset gameOver flag

        // restart timer
        pipesCooldown.start();
        gameLoop.start();

        // reset score
        score = 0;
        scoreLabel.setText("Score: " + score);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        move();
        checkCollision();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setVelocityY(-10);
        }else if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){

    }
}