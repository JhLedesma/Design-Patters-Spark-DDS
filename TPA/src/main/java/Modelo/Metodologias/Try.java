package Modelo.Metodologias;

import java.util.function.Supplier;

public abstract class Try<T> {
	protected Try() {}

	public static <U> Try<U> ofFailable(Supplier<U> f) {
		try {
			return Try.successful(f.get());
		} catch (RuntimeException e) {
			return Try.failure(e);
		}
	}
	
	public static <U> Try<U> successful(U x) {
		return new Success<>(x);
	}
	
	public static <U> Try<U> failure(RuntimeException e) {
		return new Failure<>(e);
	}
	
	public abstract boolean isSuccess();
	public abstract T get();
	public abstract T orElse(T value);
}

class Success<T> extends Try<T> {
	private final T value;

	public Success(T value) {
		this.value = value;
	}
	
	@Override
	public boolean isSuccess() {
		return true;
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public T orElse(T value) {
		return this.value;
	}
}

class Failure<T> extends Try<T> {
	private final RuntimeException e;

	public Failure(RuntimeException e) {
		this.e = e;  
	}

	@Override
	public T get() {
		throw e;
	}

	@Override
	public boolean isSuccess() {
		return false;
	}

	@Override
	public T orElse(T value) {
		return value;
	}
}