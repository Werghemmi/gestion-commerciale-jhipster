import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ReglementVenteDeleteDialogComponent } from 'app/entities/reglement-vente/reglement-vente-delete-dialog.component';
import { ReglementVenteService } from 'app/entities/reglement-vente/reglement-vente.service';

describe('Component Tests', () => {
  describe('ReglementVente Management Delete Component', () => {
    let comp: ReglementVenteDeleteDialogComponent;
    let fixture: ComponentFixture<ReglementVenteDeleteDialogComponent>;
    let service: ReglementVenteService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [ReglementVenteDeleteDialogComponent],
      })
        .overrideTemplate(ReglementVenteDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReglementVenteDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReglementVenteService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
