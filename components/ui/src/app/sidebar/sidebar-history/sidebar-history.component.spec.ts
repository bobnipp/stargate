import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SidebarHistoryComponent} from './sidebar-history.component';
import {makeNewRfi} from "../../../test_helpers/utilities";

describe('SidebarHistoryComponent', () => {
    let component: SidebarHistoryComponent;
    let fixture: ComponentFixture<SidebarHistoryComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [SidebarHistoryComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(SidebarHistoryComponent);
        component = fixture.componentInstance;
        component.record = makeNewRfi({
            recordHistory: [
                {username: 'user', date: '2018-08-12T13:00:00', action: 'Update'},
                {username: 'user', date: '2018-08-12T13:00:00', action: 'Insert'}
            ]
        })
        fixture.detectChanges();
    });

    it('creates record history from the rfi', () => {
        component.ngOnInit()
        expect(component.recordHistory).toEqual(
            [
                {username: 'user', date: '08/12/2018 13:00:00', action: 'Modified'},
                {username: 'user', date: '08/12/2018 13:00:00', action: 'Created'}
            ]
        )
    })
});
