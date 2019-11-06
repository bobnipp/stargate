import {Injectable} from "@angular/core";
import {createSelector, Store} from "@ngrx/store";
import {AppState} from "../../state/appstate";
import {map} from "rxjs/operators";
import {AbstractControl, ValidationErrors, Validator} from "@angular/forms";
import {RecordItem} from "../../models/record.model";

@Injectable()
export class LinksValidatorService implements Validator {
    private ids = {"IMM": [], "PRISM": [], "": []};

    constructor(private store: Store<AppState>) {
        store.select('rfis').forEach(rfis => this.ids["IMM"] = rfis.map(rfi => rfi.id.toString()))
        store.select('prismNominations').forEach(noms => this.ids["PRISM"] = noms.map(nom => nom.id))
    }

    validate(control: AbstractControl): ValidationErrors | null {
        const record2Id = control.get('record2Id').value;
        const record2System = control.get('record2System').value;

        const record2IdFound = this.ids[record2System].includes(record2Id)
        if (!record2IdFound) {
            return {linkIdMissing: 'Cannot find ID'}
        }
        return null
    }
}