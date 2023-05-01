public class MyHashTable<K, V> {

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}"; 
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[this.M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % M;

        index = index < 0 ? index * -1 : index;
        return index;
    }

    public void put(K key, V value) {
        int chainIndex = hash(key);
        int hashCode = key.hashCode();
        HashNode<K, V> head = chainArray[chainIndex];

        while (head != null) {
            if (head.key.equals(key) && head.key.hashCode() == hashCode) {
                return;
            }

            head = head.next;
        }

        size++;
        head = chainArray[chainIndex];
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        chainArray[chainIndex] = newNode;

        if ((1.0 * size) / M >= 0.7) {
            HashNode<K, V>[] temp = chainArray;
            chainArray = new HashNode[M * 2];
            M *= 2;
            size = 0;

            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    public V get(K key) {
        int chainIndex = hash(key);
        int hashCode = key.hashCode();

        HashNode<K, V> head = chainArray[chainIndex];

        while (head != null) {
            if (head.key.equals(key) && head.key.hashCode() == hashCode) {
                return head.value;
            }

            head = head.next;
        }

        return null;
    }

    public V remove (K key) {
        int chainIndex = hash(key);
        int hashCode = key.hashCode();

        HashNode<K, V> head = chainArray[chainIndex];
 
        HashNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key) && head.key.hashCode() == hashCode) {
                break;
            }

            prev = head;
            head = head.next;
        }
 
        if (head == null) {
            return null;
        }
        size--;
        if (prev != null) {
            prev.next = head.next;
        }
        else {
            chainArray[chainIndex] = head.next;
        }
 
        return head.value;
    }

    public boolean contains(V value) {
        for (HashNode<K,V> hashNode : chainArray) {
            while (hashNode != null) {
                if (hashNode.value.equals(value)) {
                    return true;
                }

                hashNode = hashNode.next;
            }
        }

        return false;
    }

    public K getKey(V value) {
        for (HashNode<K,V> hashNode : chainArray) {
            while (hashNode != null) {
                if (hashNode.value.equals(value)) {
                    return hashNode.key;
                }

                hashNode = hashNode.next;
            }
        }

        return null;
    }
}