#### 1. What will be printed after interrupting the thread?
- **Output:**
```
Thread was interrupted!
Thread will be finished here!!!
```
- **Explanation:**

When we call `start()` method, a new thread is created and 
the `run()` method is executed.
however, calling `theard.interrupt()` interrupts the thread while it's sleeping,
causing an exception to be thrown and `Thread was interrupted!` to be printed.
`finally` is executed in any case, so `Thread will be finished here!!!` is also printed.


#### 2. In Java, what would be the outcome if the `run()`method of a `Runnable` object is invoked directly, without initiating it inside a `Thread` object?
- **Output:**
```
Running in: main
```
- **Explanation:**

When the `run()`method of a `Runnable` object is called without creating
a new `Thread`, actually no new thread is created, and the `run()` method 
runs in the current thread, which is the main thread.

#### 3. Elaborate on the sequence of events that occur when the `join()` method of a thread (let's call it `Thread_0`) is invoked within the `Main()` method of a Java program.

- **Output:**
```
Running in: Thread-0
Back to: main
```
- **Explanation:**

When the `start()` method is called, it executes `run()` method to run on its separate thread and `Running in: Thread-0`
is printed. Invoking the `join()` method on a thread causes the calling thread (which is `main` here) to wait until the created thread (let's call it `Thread_0`)
terminates its execution and after that, the execution has returned to the main thread. Thus, the `Back to: main` is printed.