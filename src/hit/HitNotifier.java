package hit;
// ID: 209083682

/**
 * a hit notifier interface.
 */
public interface HitNotifier {

    /**
     * removes a hit listener to the object.
     * @param hl the given listener to add
     */
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.

    /**
     * removes a listener from the object that got hit.
     * @param hl the given listener to remove
     */
    void removeHitListener(HitListener hl);
}