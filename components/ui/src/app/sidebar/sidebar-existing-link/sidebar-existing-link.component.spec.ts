import {ComponentFixture, TestBed} from '@angular/core/testing'
import {SidebarExistingLinkComponent} from "./sidebar-existing-link.component";
import {ClickOutsideModule} from 'ng-click-outside'

describe('SidebarExistingLinkComponent', () => {
    let component: SidebarExistingLinkComponent
    let fixture: ComponentFixture<SidebarExistingLinkComponent>

    beforeEach(() => {

        TestBed.configureTestingModule({
            declarations: [SidebarExistingLinkComponent],
            imports: [ClickOutsideModule]
        })

        fixture = TestBed.createComponent(SidebarExistingLinkComponent)
        component = fixture.componentInstance
    })

    afterEach(() => {
        jasmine.clock().uninstall();
    })

    it('removes the highlight form control after animating the component', () => {
        jasmine.clock().install();
        const rfiLink = {
            record1Id: '1',
            record2Id: '2',
            record1System: 'FOO',
            record2System: 'BAR',
            highlight: true,
        }

        component.link = rfiLink
        expect(component.highlightState).toEqual('default')

        component.highlight()
        expect(component.highlightState).toEqual('highlighted')

        jasmine.clock().tick(1501)

        expect(component.highlightState).toEqual('default')
    })

    it('assigns a non-link class for non-existent inputLinks', () => {
        const rfiLink = {
            record1Id: '1',
            record2Id: '2',
            record1System: 'FOO',
            record2System: 'BAR',
            highlight: true,
            title: null,
            status: null
        }

        component.link = rfiLink
        component.ngOnInit()
        expect(component.linkClass).toEqual('link-id-broken')
    })

    it('assigns a link class for existent inputLinks', () => {
        const rfiLink = {
            record1Id: '1',
            record2Id: '2',
            record1System: 'FOO',
            record2System: 'BAR',
            highlight: true,
            title: 'FOO',
            status: 'whatever',
        }

        component.link = rfiLink
        component.ngOnInit()
        expect(component.linkClass).toEqual('link-id')
    })
})
