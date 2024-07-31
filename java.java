const car = document.getElementById('car');
const obstacle = document.getElementById('obstacle');
const container = document.getElementById('game-container');

let carPosition = window.innerWidth / 2 - 25;
let obstaclePosition = Math.random() * (window.innerWidth - 50);

let obstacleSpeed = 5;

function moveCar(event) {
    if (event.key === 'ArrowLeft' && carPosition > 0) {
        carPosition -= 20;
    }
    if (event.key === 'ArrowRight' && carPosition < window.innerWidth - 50) {
        carPosition += 20;
    }
    car.style.left = carPosition + 'px';
}

function moveObstacle() {
    obstaclePosition += obstacleSpeed;
    if (obstaclePosition > window.innerHeight) {
        obstaclePosition = -100;
        obstacle.style.left = Math.random() * (window.innerWidth - 50) + 'px';
    }
    obstacle.style.top = obstaclePosition + 'px';
}

function checkCollision() {
    const carRect = car.getBoundingClientRect();
    const obstacleRect = obstacle.getBoundingClientRect();
    
    if (
        carRect.left < obstacleRect.right &&
        carRect.right > obstacleRect.left &&
        carRect.top < obstacleRect.bottom &&
        carRect.bottom > obstacleRect.top
    ) {
        alert('Collision detected!');
        obstaclePosition = -100; // Reset obstacle
    }
}

document.addEventListener('keydown', moveCar);

function gameLoop() {
    moveObstacle();
    checkCollision();
    requestAnimationFrame(gameLoop);
}

gameLoop();
