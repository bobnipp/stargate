import {TestBed} from '@angular/core/testing'
import {of} from 'rxjs/index'
import {throwError} from "rxjs/internal/observable/throwError"
import {HttpClient} from "@angular/common/http"
import {FilterSystemOptionComponent} from "./filter-system-option.component";
import {MockHttpClient} from "../../../../test_helpers/mockHttpClient";
import {getDataForRecordType, RecordType} from "../../../models/record.model";
import {healthCheckDefaults} from "../../../models/health-check.model";
import Spy = jasmine.Spy;

describe('FilterSystemOptionComponent', () => {
    let component: FilterSystemOptionComponent
    let getSpy: Spy

    beforeEach(() => {
        const mockHttpClient = new MockHttpClient()
        getSpy = spyOn(mockHttpClient, 'get')

        TestBed.configureTestingModule({
            declarations: [FilterSystemOptionComponent],
            providers: [
                {provide: HttpClient, useValue: mockHttpClient},
            ]
        })

        component = TestBed.createComponent(FilterSystemOptionComponent).componentInstance
    })

    describe('initialization highlightState', () => {
        it('is initialized and updated on an interval', () => {
            component.option = {id: 0, value: getDataForRecordType(RecordType.RFI, 'title'), system: RecordType.RFI}
            getSpy.and.returnValue(of({success: true, lastsave: 4500000}))
            component.ngOnInit()

            expect(getSpy).toHaveBeenCalledWith('/api/v1/health/imm')
            expect(component.className).toEqual('up')
            expect(component.lastSaveText).toEqual('75 minutes old')

            expect(getSpy).toHaveBeenCalledTimes(1)
        })

        it('returns an unsuccessful integration health check', () => {
            component.option = {
                id: 1,
                value: getDataForRecordType(RecordType.NOMINATION, 'title'),
                system: RecordType.NOMINATION
            }
            getSpy.and.returnValue(of({success: false, lastsave: -1}))
            component.ngOnInit()
            expect(getSpy).toHaveBeenCalledWith('/api/v1/health/prism')

            expect(component.icon).toEqual('warning')
            expect(component.lastSaveText).toBeFalsy()

        })

        it('returns an error from the server', () => {
            component.option = {id: 0, value: getDataForRecordType(RecordType.RFI, 'title'), system: RecordType.RFI}
            getSpy.and.returnValue(throwError({}))
            component.ngOnInit()
            expect(getSpy).toHaveBeenCalledWith('/api/v1/health/imm')

            expect(component.className).toEqual('down')
            expect(component.lastSaveText).toBeFalsy()

        })
    })

})