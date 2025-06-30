package com.example.cstnu;

import com.example.cstnu.dto.*;
import com.example.cstnu.model.ContingencyManager;
import com.example.cstnu.model.ObservedPropositionManager;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import com.example.cstnu.helpers.CustomGraphMLWriter;
import com.example.cstnu.service.CSTNUCheckerService;
import com.example.cstnu.service.CSTNUTranslationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cstnu")
@CrossOrigin(origins = "http://localhost:8080")
public class CstnuController {





    private CSTNUCheckerService cstnuCheckerService;

    private CSTNUTranslationService cstnuTranslationService;

    //Creating a directed graph object to store the graph data in
    private DirectedSparseMultigraph directedGraph = new DirectedSparseMultigraph();

    private CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();


    private ContingencyManager contingencyManager;
    private ObservedPropositionManager observedPropositionManager;

    public CstnuController(ContingencyManager contingencyManager,
                           ObservedPropositionManager observedPropositionManager, 
                           CSTNUCheckerService cstnuCheckerService, CSTNUTranslationService cstnuTranslationService){
            this.contingencyManager = contingencyManager;
            this.observedPropositionManager = observedPropositionManager;
            this.cstnuCheckerService = cstnuCheckerService;
            this.cstnuTranslationService = cstnuTranslationService;
    }


    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Cstnu Service is UP");
    }


    @PostMapping("/translation/Activity")
    public void addActivity(@RequestBody CSTNUNodeDTO CSTNUNodeDTO) {
        cstnuTranslationService.addActivity(directedGraph, CSTNUNodeDTO);
        customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);

    }

    @PostMapping ("/translation/End")
    public void addEnd(@RequestBody CSTNUNodeDTO CSTNUNodeDTO) {
        cstnuTranslationService.addEnd(directedGraph, CSTNUNodeDTO);
        System.out.println(directedGraph);
        customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);
    }

    @PostMapping ("/translation/Start")
    public void addStart(@RequestBody CSTNUNodeDTO CSTNUNodeDTO) {
        cstnuTranslationService.addStart(directedGraph, CSTNUNodeDTO);
        customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);

    }

    @PostMapping ("/translation/XOR")
    public void addXOR(@RequestBody CSTNUNodeDTO CSTNUNodeDTO) {
         cstnuTranslationService.addXOR(directedGraph, CSTNUNodeDTO);
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        //customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);

    }

    @PostMapping ("/translation/AND")
    public void addAND(@RequestBody CSTNUNodeDTO CSTNUNodeDTO) {
         cstnuTranslationService.addAND(directedGraph, CSTNUNodeDTO);
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        //customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);

    }

     @PostMapping ("/translation/SequenceFlow")
    public void addSequenceFlow(@RequestBody CSTNUEdgeDTO cstnuEdgeDTO) {
         cstnuTranslationService.addSequenceFlow(directedGraph, cstnuEdgeDTO);
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        //customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);

    }


     @PostMapping ("/translation/Constraint")
    public void addConstraint(@RequestBody CSTNUEdgeDTO cstnuEdgeDTO) {
         cstnuTranslationService.addConstraint(directedGraph, cstnuEdgeDTO);
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        //customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);

    }


     @PutMapping ("/translation/XOR")
    public void updateXOR(@RequestBody XorDTO xorDTO){
         cstnuTranslationService.updateXOR(directedGraph, xorDTO);
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);
    }



     @PutMapping ("/translation/Activity")
    public void updateTask(@RequestBody TaskDTO taskDTO){
         cstnuTranslationService.updateTask(directedGraph, taskDTO);
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);
    }

     @PutMapping ("/translation/Branch")
    public void updateBranch(@RequestBody TaskDTO taskDTO){
         cstnuTranslationService.updateTask(directedGraph, taskDTO);
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);

    }

     @PutMapping ("/translation/Constraint")
    public ResultDTO updateConstraint(@RequestBody ConstraintDTO constraintDTO){
         cstnuTranslationService.updateConstraint(directedGraph, constraintDTO);
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);
        return cstnuCheckerService.checkCSTNU();
    }

     @DeleteMapping ("/translation/Shape/{id}")
    public ResultDTO deleteShape(@PathVariable String id){
         cstnuTranslationService.deleteShape(directedGraph, id);
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);
        return cstnuCheckerService.checkCSTNU();
    }

     @DeleteMapping ("/translation/Constraint/{id}")
    public ResultDTO deleteConstraint(@PathVariable String id){
         cstnuTranslationService.deleteConstraint(directedGraph, id);
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\IdeaProjects\\TestLibrary\\src\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);
        return cstnuCheckerService.checkCSTNU();
    }


     @GetMapping ("/translation/Save")
    public void save(){
        CustomGraphMLWriter customGraphMLWriter = new CustomGraphMLWriter();
        customGraphMLWriter.save(directedGraph, "C:\\Users\\k1lil\\desktop\\fileTEST.cstnu", "CSTNU", contingencyManager, observedPropositionManager);
        System.out.println("Saved");
    }

}
