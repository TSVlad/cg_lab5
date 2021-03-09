in vec3 inPosition;
uniform float t;

void main()
{
    gl_Position = vec3(inPosition.x, inPosition.y * cos(inPosition.y + t), inPosition.z);
}