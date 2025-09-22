#include <bits/stdc++.h>
#include <SFML/Graphics.hpp>

using namespace std;
using namespace sf;


float calcDist(Vector2f A, Vector2f B)
{
    return sqrt((A.x - B.x)*(A.x - B.x) + (A.y - B.y)*(A.y - B.y));
}

Vector2f calcVect(Vector2f A, Vector2f B, float speed)
{
    float dist = calcDist(A, B);
    float x = (B.x - A.x) * speed / dist;
    float y = (B.y - A.y) * speed / dist;

    return {x, y};
}

struct Drone
{
    RectangleShape shape;
    Vector2f direction;
    float speed;

    Drone(Vector2f position, Vector2f directionT, float speedT)
    {
        shape.setFillColor(Color::White);
        shape.setSize({2, 2});
        shape.setPosition(position);
        direction = directionT;
        speed = speedT;
    }
};

vector <Drone> drones;

int main()
{
    int n; // number of drones in between
    int distD = 30; // distance between drones
    int distM = 60; // maximum distance between drones
    cin >> n;

    for(int i=1; i<=n; i++)
    {
        drones.push_back(Drone({i*distD, 0}, {1, 0}, 0.0f));
    }

    // Main Components
    RenderWindow window(VideoMode({1000, 800}), "Drone Flight Simulator");

    RectangleShape mainDrone(Vector2f(3, 3));
    mainDrone.setFillColor(Color::White);
    mainDrone.setPosition(Vector2f(n*distD+distD, 0));

    float mainDroneSpeed = 0.015f;

    RectangleShape controller(Vector2f(3, 3));
    controller.setFillColor(Color::White);
    controller.setPosition(Vector2f(0, 0));

    float controllerSpeed = 0.01f;


    // Simulation
    while (window.isOpen())
    {
        while (const std::optional event = window.pollEvent())
        {
            if (event->is<Event::Closed>())
                window.close();
        }

        if (Keyboard::isKeyPressed(Keyboard::Scan::W))
            mainDrone.move(Vector2f(0, -mainDroneSpeed));
        if (Keyboard::isKeyPressed(Keyboard::Scan::S))
            mainDrone.move(Vector2f(0, mainDroneSpeed));
        if (Keyboard::isKeyPressed(Keyboard::Scan::A))
            mainDrone.move(Vector2f(-mainDroneSpeed, 0));
        if (Keyboard::isKeyPressed(Keyboard::Scan::D))
            mainDrone.move(Vector2f(mainDroneSpeed, 0));

        if (Keyboard::isKeyPressed(Keyboard::Scan::Up))
            controller.move(Vector2f(0, -controllerSpeed));
        if (Keyboard::isKeyPressed(Keyboard::Scan::Down))
            controller.move(Vector2f(0, controllerSpeed));
        if (Keyboard::isKeyPressed(Keyboard::Scan::Left))
            controller.move(Vector2f(-controllerSpeed, 0));
        if (Keyboard::isKeyPressed(Keyboard::Scan::Right))
            controller.move(Vector2f(controllerSpeed, 0));

        // Drones simulation
        for(int i=0; i<n-1; i++)
        {
            if(calcDist(drones[i].shape.getPosition(), drones[i+1].shape.getPosition()) > distM) drones[i].speed = 0.02f;
            else drones[i].speed = 0.0f;

            drones[i].direction = drones[i+1].shape.getPosition();
            drones[i].shape.move(calcVect(drones[i].shape.getPosition(), drones[i+1].shape.getPosition(), drones[i].speed));
        }
        if(calcDist(drones[n-1].shape.getPosition(), mainDrone.getPosition()) > distM) drones[n-1].speed = 0.02f;
        else drones[n-1].speed = 0.0f;

        drones[n-1].direction = mainDrone.getPosition();
        drones[n-1].shape.move(calcVect(drones[n-1].shape.getPosition(), mainDrone.getPosition(), drones[n-1].speed));



        window.clear(Color::Black);
        window.draw(controller);
        for(int i=0; i<n; i++) window.draw(drones[i].shape);
        window.draw(mainDrone);
        window.display();
    }

}
