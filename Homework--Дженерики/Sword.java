public class Sword <T extends Metal> {
    T material;
    public Sword(T material) {
        this.material = material;
    }
    public boolean checkEndurance() {
        if (material.getEndurance() > 49) return true;
        else return false;
    }
}

