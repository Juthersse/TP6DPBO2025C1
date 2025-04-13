# TP6DPBO2025C1

# Janji
Saya Muhammad Ichsan Khairullah dengan NIM 2306924 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program
Program ini dirancang menggunakan paradigma Object-Oriented Programming (OOP) dengan beberapa kelas utama yang memiliki peran masing-masing. Berikut adalah struktur program:
## Kelas Utama:
1. App:
   - Berfungsi sebagai entry point program.
   - Menampilkan menu awal (MainMenu) saat program dijalankan.
2. MainMenu:
   - Menyediakan antarmuka awal berupa JFrame dengan tombol "Start Game".
   - Ketika tombol ditekan, menu ditutup, dan game FlappyBird dimulai.
3. FlappyBird:
   - Panel utama tempat game berlangsung.
   - Mengatur logika permainan, seperti pergerakan pemain, pipa, deteksi tabrakan, dan skor.
## Kelas Pendukung:
4. Player:
   - Merepresentasikan burung (pemain) dalam game.
   - Menyimpan atribut seperti posisi, ukuran, gambar, dan kecepatan vertikal.
5. Pipe:
   - Merepresentasikan pipa dalam game.
   - Menyimpan atribut seperti posisi, ukuran, gambar, kecepatan horizontal, dan status apakah pipa sudah dilewati.

# Alur Program
## a. Menu Awal
1. Program dimulai dari kelas App yang memanggil MainMenu.
2. MainMenu adalah JFrame dengan ukuran 360x640 (sesuai dimensi game).
3. Menu menampilkan tombol "Start Game" dengan latar belakang gambar (assets/background.png).
4. Ketika tombol ditekan:
   - JFrame menu ditutup (dispose()).
   - JFrame baru dibuat untuk memulai game FlappyBird.
## b. Game Dimulai
1. Inisialisasi Game (FlappyBird Constructor):
   - Dimensi panel diatur ke 360x640.
   - Gambar latar belakang, burung, dan pipa dimuat dari folder assets.
   - Objek Player dibuat dengan posisi awal di tengah layar.
   - Label skor (scoreLabel) ditambahkan di bagian atas layar.
   - Timer untuk game loop (gameLoop) dan pembuatan pipa (pipesCooldown) diatur dan dijalankan.
2. Game Loop (actionPerformed):
   - Timer gameLoop berjalan dengan interval 16 ms (60 FPS).
   - Pada setiap iterasi:
     - Metode move() dipanggil untuk memperbarui posisi burung dan pipa.
     - Metode checkCollision() dipanggil untuk mendeteksi tabrakan.
     - Panel digambar ulang dengan repaint().
3. Pergerakan Burung dan Pipa (move):
   - Kecepatan vertikal burung diperbarui dengan gravitasi.
   - Posisi burung diperbarui berdasarkan kecepatan vertikal.
   - Posisi pipa diperbarui dengan kecepatan horizontal.
   - Jika burung melewati pipa bawah, skor bertambah 1, dan label skor diperbarui.
4. Deteksi Tabrakan (checkCollision):
   - Jika burung keluar dari layar bawah, game berakhir.
   - Jika burung menabrak pipa (deteksi menggunakan Rectangle.intersects()), game juga berakhir.
5. Game Over (endGame):
   - Timer gameLoop dan pipesCooldown dihentikan.
   - Pesan "Game Over" ditampilkan menggunakan JOptionPane.
   - Pemain dapat menekan tombol "R" untuk me-restart game.
6. Restart Game (restartGame):
   - Posisi burung di-reset ke posisi awal.
   - Kecepatan burung diatur ulang.
   - Semua pipa dihapus dari layar.
   - Skor di-reset ke 0, dan label skor diperbarui.
   - Timer gameLoop dan pipesCooldown dimulai kembali.
## c. Pembuatan Pipa Baru (PlacePipes):
1. Timer pipesCooldown berjalan setiap 4 detik.
2. Pada setiap iterasi, dua pipa baru dibuat:
   - Pipa atas dengan posisi acak di bagian atas layar.
   - Pipa bawah dengan jarak tertentu dari pipa atas.
3. Kedua pipa ditambahkan ke daftar pipes.

# Dokumentasi
![dokumentasi (3)](https://github.com/user-attachments/assets/adacbf77-d368-49dc-a441-d2fc5c6b9887)
