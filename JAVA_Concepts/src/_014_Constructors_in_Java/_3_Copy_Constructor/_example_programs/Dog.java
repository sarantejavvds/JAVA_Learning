package _014_Constructors_in_Java._3_Copy_Constructor._example_programs;

class Dog 
{
    String name;
    
    String breed;

    // Constructor
    public Dog(String name, String breed) 
    {
        this.name = name;
        this.breed = breed;
    }

    // Copy constructor
    public Dog(Dog dog)
    {
        this.name = dog.name;
        this.breed = dog.breed;
    }

    public static void main(String[] args) 
    {
        Dog originalDog = new Dog("Buddy", "Golden Retriever");
        Dog copiedDog = new Dog(originalDog);

        System.out.println("Original dog: " + originalDog.name + ", " + originalDog.breed); // Prints: Original dog: Buddy, Golden Retriever
        System.out.println("Copied dog: " + copiedDog.name + ", " + copiedDog.breed); // Prints: Copied dog: Buddy, Golden Retriever
    }
}