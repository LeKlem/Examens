package org.acme.optaplanner.solver;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;

import org.acme.optaplanner.model.TimeTable;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;

public class TimeTableResource {

    @Inject
    SolverManager<TimeTable, UUID> solverManager;


    public TimeTable solve(TimeTable problem) {
        UUID problemId = UUID.randomUUID();
        // Submit the problem to start solving
        SolverJob<TimeTable, UUID> solverJob = solverManager.solve(problemId, problem);
        TimeTable solution;
        try {
            // Wait until the solving ends
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        return solution;
    }

}