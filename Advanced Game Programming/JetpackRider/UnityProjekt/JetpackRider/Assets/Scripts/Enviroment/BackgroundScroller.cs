using UnityEngine;

public class BackgroundScroller : MonoBehaviour
{
    // field for the scroll speed of the background image
    [SerializeField] private float scrollSpeed;
    
    private float length;
    private float startPosition;
    private float counter;
    
    void Start()
    {
        // set startposition to determine position later
        startPosition = transform.position.x;
        
        // set the length according to the Sprite Renderer
        length = GetComponent<SpriteRenderer>().bounds.size.x;

    }

    void FixedUpdate()
    {
        // set the scroll speed according to the background image
        float distance =Time.time * 5.0f * -scrollSpeed;
        
        // The background image position will be put 2 lengths to the right to scroll endlessly
        transform.position = new Vector3(startPosition + distance + length * (int)counter, transform.position.y, transform.position.z);

        // counts how many times the background image has traveled its own length to determine when to change position
        counter = -(int)distance / length + 0.05f;
    }
}