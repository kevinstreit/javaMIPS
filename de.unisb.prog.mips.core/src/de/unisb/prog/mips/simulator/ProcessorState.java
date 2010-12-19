package de.unisb.prog.mips.simulator;


public class ProcessorState {

	public static enum ExecutionState {
		HALTED, BREAKPOINT, INTERRUPT, RUNNING
	}

	public int[] gp;
	public int pc, lo, hi;
	public int ticks;
	public volatile ExecutionState state;

	public void setContinue() {
		if (state != ExecutionState.HALTED)
			state = ExecutionState.RUNNING;
	}

	@Override
	public ProcessorState clone() {
		return new ProcessorState() {
			{
				this.gp = ProcessorState.this.gp.clone();
				this.pc = ProcessorState.this.pc;
				this.lo = ProcessorState.this.lo;
				this.hi = ProcessorState.this.hi;
				this.ticks = ProcessorState.this.ticks;
				this.state = ProcessorState.this.state;

			}
		};
	}

}
