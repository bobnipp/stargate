import {Injectable} from "@angular/core";
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {CoordinateValidators} from "../../validators/custom-validators";
import {RecordItem, RecordType} from "../../models/record.model";
import {LinksValidatorService} from "../sidebar-links/links-validator.service";
import {RfiLink} from "../../models/rfi-link.model";

@Injectable()
export class RecordFormCreatorService {
    constructor(private linksValidatorService?: LinksValidatorService) {
    }

    createRecordForm(record: RecordItem): FormGroup {
        const form = new FormGroup({
            id: new FormControl(''),
            title: new FormControl('', [Validators.required]),
            country: new FormControl(''),
            coordinates: new FormControl('', {validators: [CoordinateValidators.validateCoordinates, CoordinateValidators.validateBasicCoordinate], updateOn: 'blur'}),
            city: new FormControl(''),
            region: new FormControl(''),
            nai: new FormControl(''),
            priority: new FormControl('', [Validators.required]),
            status: new FormControl('To Do'),
            justification: new FormControl('', [Validators.required]),
            prevResearch: new FormControl(''),
            requirementTypeId: new FormControl(''),
            subWorkflowId: new FormControl(''),
            classificationId: new FormControl(''),
            caveatId: new FormControl(''),
            submittingOrgId: new FormControl(''),
            nipfCodeId: new FormControl(''),
            pirNameId: new FormControl(''),
            centcomIsrRoleId: new FormControl(''),
            poc: new FormControl(''),
            customClassification: new FormControl(''),
            operation: new FormControl(''),
            collectionStartDate: new FormControl(''),
            collectionEndDate: new FormControl(''),
            collectionTerm: new FormControl(''),
            collectionType: new FormControl(''),
            assignedTeamId: new FormControl(''),
            assignee: new FormControl(''),
            collectionGuidance: new FormControl(''),
            recordType: new FormControl(RecordType.RFI)
        })

        this.addFormArrayElements(form, record)

        return form
    }

    createLinkForm(link?: RfiLink): FormGroup {
        let formGroup = new FormGroup({
            record1Id: new FormControl(''),
            record2Id: new FormControl(''),
            record1System: new FormControl(''),
            record2System: new FormControl('')
        }, {validators: [this.linksValidatorService.validate.bind(this.linksValidatorService)]})

        if (link) {
            formGroup.patchValue(link)
        }
        return formGroup
    }

    public addFormArrayElements(form: FormGroup, record: RecordItem) {
        this.addEeis(form, record)
        this.addTargets(form, record)
        this.addSensors(form, record)

        form.patchValue(record)
    }

    private addTargets(form: FormGroup, record: RecordItem) {
        const targets = new FormArray([])
        if (record.targets && record.targets.length > 0) {
            record.targets.forEach(() => {
                targets.push(
                    new FormGroup({
                        id: new FormControl(''),
                        rfiId: new FormControl(''),
                        name: new FormControl(''),
                        type: new FormControl(''),
                        radius: new FormControl(),
                        radiusUnit: new FormControl(''),
                        coordinates: new FormControl('', {
                            validators: [CoordinateValidators.validateCoordinates],
                            updateOn: 'blur'
                        })
                    }, {validators: CoordinateValidators.validateTargetCoordinates, updateOn: 'change'}))
            })
        } else {
            targets.push(
                new FormGroup({
                    id: new FormControl(''),
                    rfiId: new FormControl(!!record.id ? record.id : ''),
                    name: new FormControl(''),
                    type: new FormControl(''),
                    radius: new FormControl(),
                    radiusUnit: new FormControl('NM'),
                    coordinates: new FormControl('', {
                        validators: [CoordinateValidators.validateCoordinates],
                        updateOn: 'blur'
                    })
                }, {validators: CoordinateValidators.validateTargetCoordinates, updateOn: 'change'}))
        }
        form.setControl('targets', targets)
    }

    private addSensors(form: FormGroup, record: RecordItem) {
        const sensors = new FormArray([])
        if (record.sensors && record.sensors.length > 0) {
            record.sensors.forEach(() => {
                sensors.push(
                    new FormGroup({
                        id: new FormControl(''),
                        rfiId: new FormControl(''),
                        sensor: new FormControl(''),
                        sensorType: new FormControl(''),
                        mode: new FormControl(),
                        desiredQuality: new FormControl(''),
                        requiredQuality: new FormControl('')
                    }))
            })
        } else {
            sensors.push(
                new FormGroup({
                    id: new FormControl(''),
                    rfiId: new FormControl(!!record.id ? record.id : ''),
                    sensor: new FormControl(''),
                    sensorType: new FormControl(''),
                    mode: new FormControl(),
                    desiredQuality: new FormControl(''),
                    requiredQuality: new FormControl('')
                }))
        }
        form.setControl('sensors', sensors)
    }

    private addEeis(form: FormGroup, record: RecordItem) {
        const eeis = new FormArray([
            new FormControl('')
        ])

        const length = record.eeis ? record.eeis.length - 1 : 0

        if (length > 0) {
            for (let i = 0; i < length; i++) {
                eeis.push(new FormControl(''))
            }
        }
        form.setControl('eeis', eeis)
    }
}