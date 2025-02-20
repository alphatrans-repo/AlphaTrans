#!/bin/bash

PROJECT=$1
TEMPERATURE=$2
MODEL=$3
RECOMPOSE_EVOSUITE=$4

if [[ $RECOMPOSE_EVOSUITE = 'y' ]]; then
    python3 src/postprocessing/recompose.py --project_name=$PROJECT \
                                            --model_name=$MODEL \
                                            --output_dir=data/recomposed_projects \
                                            --type=body \
                                            --temperature=$TEMPERATURE \
                                            --suffix=_decomposed_tests \
                                            --recompose_evosuite
else
    python3 src/postprocessing/recompose.py --project_name=$PROJECT \
                                            --model_name=$MODEL \
                                            --output_dir=data/recomposed_projects \
                                            --type=body \
                                            --temperature=$TEMPERATURE \
                                            --suffix=_decomposed_tests
fi
